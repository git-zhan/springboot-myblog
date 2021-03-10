package com.zxx.blog.config.login;

import com.zxx.blog.config.model.JwtUserDetails;
import com.zxx.blog.entity.security.User;
import com.zxx.blog.service.security.impl.UserServiceImpl;
import com.zxx.blog.util.data.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	JwtUserDetails userDetail = null;
    	try {
    		if(StringUtils.isBlank(userName)){
                throw new UsernameNotFoundException("用户名不能为空!");
            }
    		User user = userService.findByName(userName);
            userDetail = new JwtUserDetails(user);
		} catch (Exception e) {
			throw e;
		}
    	return userDetail;
    }
}
