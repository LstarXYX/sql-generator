package com.lstar.sql_generator_server.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lstar.sql_generator_server.common.Exception.AppException;
import com.lstar.sql_generator_server.common.utils.Const;
import com.lstar.sql_generator_server.mapper.UserMapper;
import com.lstar.sql_generator_server.model.User;
import com.lstar.sql_generator_server.model.enu.ErrorCode;
import com.lstar.sql_generator_server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService
{

	@Resource
	private UserMapper userMapper;

	//盐值 混肴
	private static final String SALT = "lstar";

	@Override
	public Integer userRegister(String userName, String password, String checkPassword, String role)
	{
		if (StringUtils.isAnyBlank(userName, password, checkPassword))
			throw new AppException(ErrorCode.PARAMS_ERROR);
		if (userName.length() > Const.LENGTH_INT_16)
			throw new AppException(ErrorCode.PARAMS_ERROR, "用户名过长");
		if (password.length() < Const.LENGTH_INT_8 || checkPassword.length() < Const.LENGTH_INT_8)
			throw new AppException(ErrorCode.PARAMS_ERROR, "密码过短");
		if (!password.equals(checkPassword))
			throw new AppException(ErrorCode.PARAMS_ERROR, "两次密码不一致");

		synchronized (userName.intern())
		{
			QueryWrapper<User> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("user_name", userName);
			Long count = userMapper.selectCount(queryWrapper);
			if (count > Const.ZERO_LONG)
				throw new AppException(ErrorCode.PARAMS_ERROR, "账号重复");

			//加密
			String encryptPassword = DigestUtil.md5Hex((SALT + password).getBytes());

			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			user.setRole(role);

			boolean save = this.save(user);
			if (!save)
				throw new AppException(ErrorCode.SYSTEM_ERROR);
			return user.getRecId();
		}
	}

	@Override
	public User userLogin(String userName, String userPassword, HttpServletRequest request)
	{
		// 1. 校验
		if (StringUtils.isAnyBlank(userName, userPassword)) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		if (userName.length() < 4) {
			throw new AppException(ErrorCode.PARAMS_ERROR, "账号错误");
		}
		if (userPassword.length() < 8) {
			throw new AppException(ErrorCode.PARAMS_ERROR, "密码错误");
		}
		// 2. 加密
		String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
		// 查询用户是否存在
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name", userName);
		queryWrapper.eq("`password`", encryptPassword);
		User user = userMapper.selectOne(queryWrapper);
		// 用户不存在
		if (user == null) {
			log.info("user login failed, userAccount cannot match userPassword");
			throw new AppException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
		}
		// 3. 记录用户的登录态
		request.getSession().setAttribute(User.KEY_USER_LOGIN_STATUS, user);
		return user;
	}

	@Override
	public User getLoginUser(HttpServletRequest request)
	{
		// 先判断是否已登录
		Object userObj = request.getSession().getAttribute(User.KEY_USER_LOGIN_STATUS);
		User currentUser = (User) userObj;
		if (currentUser == null || currentUser.getRecId() == null) {
			throw new AppException(ErrorCode.NOT_LOGIN_ERROR);
		}
		// 从数据库查询（追求性能的话可以注释，直接走缓存）
		long userId = currentUser.getRecId();
		currentUser = this.getById(userId);
		if (currentUser == null) {
			throw new AppException(ErrorCode.NOT_LOGIN_ERROR);
		}
		return currentUser;
	}

	@Override
	public boolean isAdmin(HttpServletRequest request)
	{
		// 仅管理员可查询
		Object userObj = request.getSession().getAttribute(User.KEY_USER_LOGIN_STATUS);
		User user = (User) userObj;
		return user != null && User.ROLE_ADMIN.equals(user.getRole());
	}

	@Override
	public boolean userLogout(HttpServletRequest request)
	{
		if (request.getSession().getAttribute(User.KEY_USER_LOGIN_STATUS) == null) {
			throw new AppException(ErrorCode.NOT_LOGIN_ERROR, "未登录");
		}
		// 移除登录态
		request.getSession().removeAttribute(User.KEY_USER_LOGIN_STATUS);
		return true;
	}
}
