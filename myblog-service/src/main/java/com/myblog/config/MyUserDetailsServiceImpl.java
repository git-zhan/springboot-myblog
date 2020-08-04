package com.myblog.config;

import com.myblog.entity.security.SecurityUser;
import com.myblog.exception.ClientException;
import com.myblog.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SecurityUser securityUser = userService.findByName(username);
        if(securityUser == null){
            throw new ClientException(ClientException.USER_NOT_EXIST);
        }
        return securityUser;
    }
}
