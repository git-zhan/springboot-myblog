package com.zxx.blog.config.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.zxx.blog.config.model.JwtUserDetails;
import com.zxx.blog.entity.security.User;

public class WebUtils {
	
	
	private static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	private static User getUser() {
		return ((JwtUserDetails)getAuthentication().getPrincipal()).getUser();
	}
	
	public static String getUserName() {
		return getUser().getUserName();
	}
	
	public static String getUserId() {
		return getUser().getObjectId();
	}
}
