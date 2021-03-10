package com.zxx.blog.service.security.impl;

import com.zxx.blog.entity.security.User;
import com.zxx.blog.mapper.security.UserMapper;
import com.zxx.blog.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findById(String objectId) {
        return userMapper.findById(objectId);
    }

    @Override
    public User findByName(String userName) {
        return userMapper.findByName(userName);
    }

    @Override
    public void save(User user) {
        if(user.getObjectId() != null){
            userMapper.update(user);
        }else{
            userMapper.insert(user);
        }
    }

    @Override
    public void delete(String objectId) {
    	
    }
}
