package com.myblog.service.security.impl;

import com.myblog.entity.security.SecurityUser;
import com.myblog.mapper.security.UserMapper;
import com.myblog.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public SecurityUser findById(Integer objectId) {
        return userMapper.findById(objectId);
    }

    @Override
    public SecurityUser findByName(String userName) {
        return userMapper.findByName(userName);
    }

    @Override
    public Integer insert(SecurityUser user) {
        return null;
    }

    @Override
    public Integer update(SecurityUser user) {
        return null;
    }

    @Override
    public Integer delete(Integer objectId) {
        return null;
    }

    @Override
    public List<SecurityUser> findAll() {
        return null;
    }

    @Override
    public List<SecurityUser> findByPage() {
        return null;
    }
}
