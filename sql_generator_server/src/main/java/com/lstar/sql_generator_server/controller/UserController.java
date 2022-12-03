package com.lstar.sql_generator_server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.lstar.sql_generator_server.common.Exception.AppException;
import com.lstar.sql_generator_server.common.anno.Auth;
import com.lstar.sql_generator_server.common.result.Response;
import com.lstar.sql_generator_server.common.utils.Result;
import com.lstar.sql_generator_server.model.User;
import com.lstar.sql_generator_server.model.enu.ErrorCode;
import com.lstar.sql_generator_server.model.request.DeleteRequest;
import com.lstar.sql_generator_server.model.request.UserAddRequest;
import com.lstar.sql_generator_server.model.request.UserLoginRequest;
import com.lstar.sql_generator_server.model.request.UserQueryRequest;
import com.lstar.sql_generator_server.model.request.UserRegisterRequest;
import com.lstar.sql_generator_server.model.request.UserUpdateRequest;
import com.lstar.sql_generator_server.model.vo.UserVo;
import com.lstar.sql_generator_server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController
{
	@Resource
	private UserService userService;

	/**
	 * 用户注册
	 * @param request
	 * @return userId
	 */
	@ApiOperation(value = "用户注册")
	@PostMapping("/register")
	public Response registerUser(@RequestBody UserRegisterRequest request)
	{
		if (request == null)
			throw new AppException(ErrorCode.PARAMS_ERROR);
		String userName = request.getUserName();
		String password = request.getPassword();
		String checkPassword = request.getCheckPassword();
		if (StringUtils.isAnyBlank(userName, password, checkPassword))
			throw new AppException(ErrorCode.PARAMS_ERROR);

		Integer userId = userService.userRegister(userName, password, checkPassword, User.ROLE_DEFAULT);
		return Result.success(userId);
	}

	/**
	 * 用户登录
	 *
	 * @param userLoginRequest
	 * @param request
	 * @return 
	 */
	@PostMapping("/login")
	@ApiOperation(value = "用户登录")
	public Response userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
		if (userLoginRequest == null) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		String userName = userLoginRequest.getUserName();
		String userPassword = userLoginRequest.getPassword();
		if (StringUtils.isAnyBlank(userName, userPassword)) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		User user = userService.userLogin(userName, userPassword, request);
		return Result.success(user);
	}

	/**
	 * 用户注销
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/logout")
	@ApiOperation(value = "注销")
	public Response userLogout(HttpServletRequest request) {
		if (request == null) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		boolean result = userService.userLogout(request);
		return Result.success(result);
	}

	/**
	 * 获取当前登录用户
	 *
	 * @param request
	 * @return
	 */
	@GetMapping("/get/login")
	@ApiOperation(value = "获取当前登录用户")
	public Response getLoginUser(HttpServletRequest request) {
		User user = userService.getLoginUser(request);
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user, userVo);
		return Result.success(userVo);
	}

	/**
	 * 创建用户
	 *
	 * @param userAddRequest
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "创建用户")
	@PostMapping("/add")
	@Auth(require = User.ROLE_ADMIN)
	public Response addUser(@RequestBody UserAddRequest userAddRequest, HttpServletRequest request) {
		if (userAddRequest == null) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		User user = new User();
		BeanUtils.copyProperties(userAddRequest, user);
		boolean result = userService.save(user);
		if (!result) {
			throw new AppException(ErrorCode.SYSTEM_ERROR);
		}
		return Result.success(user.getRecId());
	}

	/**
	 * 删除用户
	 *
	 * @param deleteRequest
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "删除用户")
	@PostMapping("/delete")
	@Auth(require = User.ROLE_ADMIN)
	public Response deleteUser(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
		if (deleteRequest == null || deleteRequest.getRecId() <= 0) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		boolean b = userService.removeById(deleteRequest.getRecId());
		return Result.success(b);
	}

	/**
	 * 更新用户
	 *
	 * @param userUpdateRequest
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "更新用户")
	@PostMapping("/update")
	@Auth(require = User.ROLE_ADMIN)
	public Response updateUser(@RequestBody UserUpdateRequest userUpdateRequest,
			HttpServletRequest request) {
		if (userUpdateRequest == null || userUpdateRequest.getRecId() == null) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		User user = new User();
		BeanUtils.copyProperties(userUpdateRequest, user);
		boolean result = userService.updateById(user);
		return Result.success(result);
	}

	/**
	 * 根据 id 获取用户
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据 id 获取用户")
	@GetMapping("/get")
	@Auth(require = User.ROLE_ADMIN)
	public Response getUserById(int id, HttpServletRequest request) {
		if (id <= 0) {
			throw new AppException(ErrorCode.PARAMS_ERROR);
		}
		User user = userService.getById(id);
		UserVo userVO = new UserVo();
		BeanUtils.copyProperties(user, userVO);
		return Result.success(userVO);
	}

	/**
	 * 获取用户列表
	 *
	 * @param userQueryRequest
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "获取用户列表")
	@GetMapping("/list")
	@Auth(require = User.ROLE_ADMIN)
	public Response listUser(UserQueryRequest userQueryRequest, HttpServletRequest request) {
		User userQuery = new User();
		if (userQueryRequest != null) {
			BeanUtils.copyProperties(userQueryRequest, userQuery);
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
		List<User> userList = userService.list(queryWrapper);
		List<UserVo> userVOList = userList.stream().map(user -> {
			UserVo userVO = new UserVo();
			BeanUtils.copyProperties(user, userVO);
			return userVO;
		}).collect(Collectors.toList());
		return Result.success(userVOList);
	}

	/**
	 * 分页获取用户列表
	 *
	 * @param userQueryRequest
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "分页获取用户列表")
	@GetMapping("/list/page")
	@Auth(require = User.ROLE_ADMIN)
	public Response listUserByPage(UserQueryRequest userQueryRequest, HttpServletRequest request) {
		long current = 1;
		long size = 10;
		User userQuery = new User();
		if (userQueryRequest != null) {
			BeanUtils.copyProperties(userQueryRequest, userQuery);
			current = userQueryRequest.getCurrent();
			size = userQueryRequest.getPageSize();
		}
		QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
		Page<User> userPage = userService.page(new Page<>(current, size), queryWrapper);
		Page<UserVo> userVOPage = new PageDTO<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
		List<UserVo> userVOList = userPage.getRecords().stream().map(user -> {
			UserVo userVO = new UserVo();
			BeanUtils.copyProperties(user, userVO);
			return userVO;
		}).collect(Collectors.toList());
		userVOPage.setRecords(userVOList);
		return Result.success(userVOPage);
	}

}
