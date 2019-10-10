package com.jumeng.auditcar.web.security;

import com.jumeng.auditcar.web.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.token}")
    private String tokenHeader;
    @Value("${jwt.startStr}")
    private String tokenStartStr;
	@Autowired
	private JWTService jwtService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(tokenStartStr)) {
            String authToke = authHeader.substring(tokenStartStr.length());
            String userId = jwtService.getUserIdFromToken(authToke);
            String authRedisKey = jwtService.getAuthRedisKey(userId);
            Object redisToken = redisUtil.get(authRedisKey);
            if(redisToken != null && authHeader.equals(redisToken.toString())) {
                if (StringUtils.isNotBlank(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = jwtService.getUserFromToken(authToke);
                    if (jwtService.validateToken(authToke, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }else{
                        redisUtil.del(authRedisKey);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
    
}
