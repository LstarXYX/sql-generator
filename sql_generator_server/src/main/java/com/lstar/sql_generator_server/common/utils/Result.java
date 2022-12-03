package com.lstar.sql_generator_server.common.utils;

import com.lstar.sql_generator_server.common.result.Response;
import com.lstar.sql_generator_server.model.enu.ErrorCode;

/**
 * 返回结果工具类
 */
public class Result
{
	private Result(){}

	public static Response success(Object data)
	{
		return new Response(0, data, "ok");
	}

	public static Response fail(ErrorCode errorCode)
	{
		return new Response(errorCode);
	}

	public static Response fail(int code, String msg)
	{
		return new Response(code, msg);
	}

	public static Response fail(ErrorCode errorCode, String msg)
	{
		return new Response(errorCode.getCode(), null, msg);
	}
}
