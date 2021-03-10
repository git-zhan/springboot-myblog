package com.zxx.blog.config.login;

import com.zxx.blog.entity.security.User;
import com.zxx.blog.util.data.JSONUtils;
import com.zxx.blog.util.io.IOUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    protected LoginAuthenticationFilter() {
		super(new AntPathRequestMatcher("/blog/login","POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authRequest = null;
		try {
			if (!request.getMethod().equals("POST")) {
	            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
	        } else {
	        	String body = IOUtils.readAsText(request.getInputStream());
	            User user = JSONUtils.strToObj(body,User.class);
	            authRequest = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
	            this.setDetails(request, authRequest);
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return this.getAuthenticationManager().authenticate(authRequest);
    }
	
	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
