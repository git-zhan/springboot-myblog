package com.zxx.blog.config.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zxx.blog.model.request.RequestError;
import com.zxx.blog.util.data.StringUtils;
import com.zxx.blog.util.encrypt.JwtUtils;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private JwtUserDetailsService userDetailService;
	
	@Autowired
	private LoginExceptionHandler loginExceptionHandler;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = request.getHeader(jwtUtils.getHeader());
			if(StringUtils.isNotBlank(token)) {
				String userName = jwtUtils.getUserName(token);
				if(StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
					if(jwtUtils.isTokenExpired(token)) {
						throw new InvalidCookieException(RequestError.TOKEN_EXPIRED.getMessage());
					}
					UserDetails userDetails = userDetailService.loadUserByUsername(userName);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,null);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}else {
					throw new InvalidCookieException(RequestError.TOKEN_EXPIRED.getMessage());
				}
			}
		} catch (InvalidCookieException e) {
			SecurityContextHolder.clearContext();
            this.loginExceptionHandler.commence(request, response, e);
            return;
		}
		filterChain.doFilter(request, response);
	}

}
