package com.jumeng.auditcar.web.security;

import com.jumeng.auditcar.common.DateStrUtil;
import com.jumeng.auditcar.common.JsonUtil;
import com.jumeng.auditcar.domain.exception.AuthErrorType;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response = WebUtils.initResponse(response);
		response.getWriter().write(JsonUtil.toJson(Result.fail(AuthErrorType.FORBIDDEN_NOT_LOGIN), DateStrUtil.dateFormat));
	}

}
