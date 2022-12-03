package com.lstar.sql_generator_server.common.interceptor;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志记录 aop
 */
@Aspect
@Component
@Slf4j
public class LogInterceptor
{

	@Around("execution(* com.lstar.sql_generator_server.controller.*.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable
	{

		//获取请求路径
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

		String requestId = UUID.randomUUID().toString();
		String url = request.getRequestURI();
		Object[] args = joinPoint.getArgs();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String params = "[" + StringUtils.join(args, ",") + "]";

		log.info("request url={}, className={}, methodName={}, params={}, requestId={}", url, className, methodName, params, requestId);

		StopWatch stopWatch = new StopWatch();

		try
		{
			stopWatch.start();
			Object result = joinPoint.proceed();
			stopWatch.stop();

			long costTime = stopWatch.getTotalTimeMillis();
			log.info("response result={}, requestId={}, cost={}", JSONUtil.toJsonStr(result), requestId, costTime);

			return result;
		}
		catch (Exception e)
		{
			stopWatch.stop();
			long costTime = stopWatch.getTotalTimeMillis();
			log.info("response error requestId={}, cost={}", requestId, costTime);
			throw e;
		}
	}

}
