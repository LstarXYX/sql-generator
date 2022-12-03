package com.lstar.sql_generator_server.common.Exception;

import com.lstar.sql_generator_server.model.enu.ErrorCode;

public class AppException extends RuntimeException
{
	private final ErrorCode errorCode;


	public AppException(ErrorCode errorCode)
	{
		super(errorCode.getMsg());
		this.errorCode = errorCode;
	}

	public AppException(ErrorCode errorCode, String msg)
	{
		super(msg);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}
}
