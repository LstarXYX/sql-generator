package com.lstar.sql_generator_server.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("创建用户请求体")
public class UserAddRequest implements Serializable
{
	private static final long serialVersionUID = -4461196517834728473L;

	/**
	 * 用户昵称
	 */
	@ApiModelProperty("用户名")
	private String userName;

	/**
	 * 用户角色: user, admin
	 */
	@ApiModelProperty("角色")
	private String role;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;
}
