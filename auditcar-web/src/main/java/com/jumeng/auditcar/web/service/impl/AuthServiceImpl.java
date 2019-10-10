package com.jumeng.auditcar.web.service.impl;

import com.jumeng.auditcar.web.security.JWTUserDetails;
import com.jumeng.auditcar.web.security.TokenAdminDetailsService;
import com.jumeng.auditcar.web.security.TokenMerchantDetailsService;
import com.jumeng.auditcar.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenMerchantDetailsService tokenMerchantDetailsService;
	@Autowired
	private TokenAdminDetailsService tokenAdminDetailsService;

	@Override
	public JWTUserDetails merchantLogin(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final JWTUserDetails userDetails = tokenMerchantDetailsService.loadUserByUsername(username);
        return userDetails;
	}

	@Override
	public JWTUserDetails adminLogin(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		final Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final JWTUserDetails userDetails = tokenAdminDetailsService.loadUserByUsername(username);
		return userDetails;
	}
}
