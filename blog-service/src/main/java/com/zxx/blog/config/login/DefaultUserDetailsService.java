package com.zxx.blog.config.login;

import com.zxx.blog.entity.security.Role;
import com.zxx.blog.entity.security.User;
import com.zxx.blog.service.security.impl.RoleServiceImpl;
import com.zxx.blog.service.security.impl.UserServiceImpl;
import com.zxx.blog.util.data.StringUtils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	if(StringUtils.isBlank(userName)){
            throw new NullPointerException("用户名不能为空!");
        }
    	User user = userService.findByName(userName);
		if(user == null){
            throw new UsernameNotFoundException("用户名" + userName + "不存在!");
        }
		List<Role> roleList = roleService.getRoleListByUser(user.getObjectId());
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority auth = null;
        for(Role role : roleList) {
        	auth = new SimpleGrantedAuthority(role.getName());
        	authList.add(auth);
        }
        return new UserDetail(user, authList);
    }
}
