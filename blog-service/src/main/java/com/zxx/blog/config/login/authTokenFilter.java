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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  自定义获取登录名和密码
 * @author zxx
 *
 */
public class authTokenFilter extends AbstractAuthenticationProcessingFilter {
	
    protected authTokenFilter() {
		super(new AntPathRequestMatcher("/blog/login","POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
		if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
		UsernamePasswordAuthenticationToken authToken = null;
		String body = IOUtils.readAsText(request.getInputStream());
		User user = JSONUtils.strToObj(body,User.class);
		authToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
		this.setDetails(request, authToken);
		return this.getAuthenticationManager().authenticate(authToken);
    }
	
	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
	
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
