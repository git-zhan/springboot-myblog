package com.zxx.blog.config.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.zxx.blog.model.request.Response;
import com.zxx.blog.util.data.JSONUtils;
import com.zxx.blog.util.encrypt.JwtUtils;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse resp,
			Authentication authentication) throws IOException, ServletException {
		String token = jwtUtils.generateToken(authentication.getName());
		resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    	resp.setCharacterEncoding("utf-8");
    	PrintWriter print = resp.getWriter();
    	print.print(JSONUtils.objToStr(Response.success(token)));
    	print.flush();
		print.close();
	}
}
