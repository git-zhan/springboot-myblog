package com.zxx.blog.config.login;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Component;

import com.zxx.blog.model.request.RequestError;
import com.zxx.blog.model.request.Response;
import com.zxx.blog.util.data.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class LoginExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
    	resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
    	resp.setCharacterEncoding("utf-8");
    	PrintWriter print = resp.getWriter();
    	if(e instanceof InvalidCookieException) {
    		print.print(JSONUtils.objToStr(Response.error(RequestError.TOKEN_EXPIRED)));
    	}else {
    		print.print(JSONUtils.objToStr(Response.error(RequestError.AUTHOUTHRITY_ERROR)));
    	}
    	print.flush();
		print.close();
    }
}
