package com.lstar.sql_generator_server.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求实体
 */
@ApiModel(value = "用户登录请求实体")
@Data
public class UserLoginRequest implements Serializable
{
	private static final long serialVersionUID = 7211589841176951662L;

	@ApiModelProperty(value = "用户名", dataType = "String", required = true)
	private String userName;
	@ApiModelProperty(value = "密码", dataType = "String", required = true)
	private String password;
}
