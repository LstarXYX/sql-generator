package com.lstar.sql_generator_server.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "用户注册实体", description = "用户注册请求体")
@Data
public class UserRegisterRequest implements Serializable
{
	private static final long serialVersionUID = 927162023167977002L;

	@ApiModelProperty(value = "用户名", dataType = "String", required = true)
	private String userName;
	@ApiModelProperty(value = "密码", dataType = "String", required = true)
	private String password;
	@ApiModelProperty(value = "校验密码", dataType = "String", required = true)
	private String checkPassword;

}
