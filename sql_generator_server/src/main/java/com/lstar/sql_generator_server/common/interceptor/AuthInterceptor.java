package com.lstar.sql_generator_server.common.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import com.lstar.sql_generator_server.common.Exception.AppException;
import com.lstar.sql_generator_server.common.anno.Auth;
import com.lstar.sql_generator_server.model.User;
import com.lstar.sql_generator_server.model.enu.ErrorCode;
import com.lstar.sql_generator_server.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class AuthInterceptor
{

	@Resource
	private UserService userService;

	@Around("@annotation(auth)")
	public Object authCheck(ProceedingJoinPoint joinPoint, Auth auth) throws Throwable
	{
		List<String> anyRole = Arrays.stream(auth.anyRole()).filter(StringUtils::isNotBlank)
				.collect(Collectors.toList());
		String requireRole = auth.require();

		if (anyRole.isEmpty() && StringUtils.isBlank(requireRole))
			return joinPoint.proceed();

		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

		//获取当前登录用户
		User user = userService.getLoginUser(request);
		String role = user.getRole();

		if (CollectionUtil.isNotEmpty(anyRole) && !anyRole.contains(role))
			throw new AppException(ErrorCode.NO_AUTH_ERROR);

		if(StringUtils.isNotBlank(requireRole) && requireRole.equals(role))
			throw new AppException(ErrorCode.NO_AUTH_ERROR);

		return joinPoint.proceed();
	}

}
