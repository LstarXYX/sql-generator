package com.lstar.sql_generator_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lstar.sql_generator_server.model.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User>
{
	/**
	 * 用户注册
	 *
	 * @param userName 用户名 用户账户
	 * @param password 用户密码
	 * @param checkPassword 校验密码
	 * @param role 用户角色
	 * @return 新用户 id
	 */
	Integer userRegister(String userName, String password, String checkPassword, String role);

	/**
	 * 用户登录
	 *
	 * @param userAccount 用户账户
	 * @param userPassword 用户密码
	 * @param request
	 * @return 脱敏后的用户信息
	 */
	User userLogin(String userAccount, String userPassword, HttpServletRequest request);

	/**
	 * 获取当前登录用户
	 *
	 * @param request
	 * @return
	 */
	User getLoginUser(HttpServletRequest request);

	/**
	 * 是否为管理员
	 *
	 * @param request
	 * @return
	 */
	boolean isAdmin(HttpServletRequest request);

	/**
	 * 用户注销
	 */
	boolean userLogout(HttpServletRequest request);
}
