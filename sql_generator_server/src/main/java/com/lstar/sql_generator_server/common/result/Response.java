package com.lstar.sql_generator_server.common.result;

import com.lstar.sql_generator_server.model.enu.ErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable
{
	private static final long serialVersionUID = -1724439598321960336L;

	private int code;
	private Object data;
	private String message;

	public Response(int code, Object data, String message)
	{
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public Response(int code, Object data)
	{
		this.code = code;
		this.data = data;
	}

	public Response(ErrorCode errorCode)
	{
		this(errorCode.getCode(), null, errorCode.getMsg());
	}
}
