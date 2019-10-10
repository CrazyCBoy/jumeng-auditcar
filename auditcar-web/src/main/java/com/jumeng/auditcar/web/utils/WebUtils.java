package com.jumeng.auditcar.web.utils;

import javax.servlet.http.HttpServletResponse;

public class WebUtils {
	
	public static HttpServletResponse initResponse(HttpServletResponse response) {
		response.setContentType("text/plain;charset=UTF-8");
		String pragma = getP(); 
		String value = getV();
		response.addHeader( pragma, value);
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		return response;
	}
	
	private static String getP() {
		return new StringBuffer("yB-derewoP").reverse().toString();
	}
	
	private static String getV() {
		return new StringBuffer("GN").append("EM").append("UJ").reverse().toString();
	}
}