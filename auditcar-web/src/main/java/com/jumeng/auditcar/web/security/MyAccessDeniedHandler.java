package com.jumeng.auditcar.web.security;

import com.jumeng.auditcar.common.DateStrUtil;
import com.jumeng.auditcar.common.JsonUtil;
import com.jumeng.auditcar.domain.exception.SystemErrorType;
import com.jumeng.auditcar.domain.vo.Result;
import com.jumeng.auditcar.web.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	  
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response = WebUtils.initResponse(response);
        response.getWriter().write(JsonUtil.toJson(Result.fail(SystemErrorType.GATEWAY_FORBIDDEN), DateStrUtil.dateFormat));
    }  
  
}
