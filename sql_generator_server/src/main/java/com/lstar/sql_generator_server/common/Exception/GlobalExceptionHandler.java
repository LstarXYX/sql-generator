package com.lstar.sql_generator_server.common.Exception;

import com.lstar.sql_generator_server.common.result.Response;
import com.lstar.sql_generator_server.common.utils.Result;
import com.lstar.sql_generator_server.model.enu.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
	@ExceptionHandler(AppException.class)
	public Response appExceptionHandler(AppException e)
	{
		log.error("AppException: {}", e.getMessage());
		return Result.fail(e.getErrorCode(), e.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public Response runtimeExceptionHandler(RuntimeException e)
	{
		log.error("runtimeException: ", e);
		return Result.fail(ErrorCode.SYSTEM_ERROR, e.getMessage());
	}
}
