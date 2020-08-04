package com.myblog.service.security;

import com.myblog.entity.security.SecurityUser;

import java.util.List;

public interface UserService {
    SecurityUser findById(Integer objectId);

    SecurityUser findByName(String userName);

    Integer insert(SecurityUser user);

    Integer update(SecurityUser user);

    Integer delete(Integer objectId);

    List<SecurityUser> findAll();

    List<SecurityUser> findByPage();
}
