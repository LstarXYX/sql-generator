package com.lstar.sql_generator_server.model.enu;

public enum ErrorCode
{
	PARAMS_ERROR(99, "参数错误"),
	SYSTEM_ERROR(999, "系统错误"),
	NOT_LOGIN_ERROR(201, "用户未登录"),
	NO_AUTH_ERROR(3100, "没有权限");

	private Integer code;
	private String msg;

	ErrorCode(Integer code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode()
	{
		return code;
	}

	public String getMsg()
	{
		return msg;
	}
}
