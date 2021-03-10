package com.zxx.blog.config.login;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.zxx.blog.model.request.RequestError;
import com.zxx.blog.model.request.Response;
import com.zxx.blog.util.data.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        Response resp = null;
        if(e instanceof UsernameNotFoundException) {
        	resp = Response.error(RequestError.USERNAME_ERROR);
        } else if(e instanceof BadCredentialsException) {
        	resp = Response.error(RequestError.PASSWORD_ERROR);
        } else if (e instanceof LockedException) {
        	resp = Response.error(RequestError.ACCOUNT_LOCKED_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
        	resp = Response.error(RequestError.PASSWORD_ERROR);
        } else if (e instanceof AccountExpiredException) {
        	resp = Response.error(RequestError.USERNAME_ERROR);
        } else if (e instanceof DisabledException) {
        	resp = Response.error(RequestError.USERNAME_ERROR);
        } else {
        	resp = Response.error(RequestError.USERNAME_ERROR);
        }
        httpServletResponse.getWriter().print(JSONUtils.objToStr(resp));
    }
}
