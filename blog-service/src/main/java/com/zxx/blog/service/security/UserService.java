package com.zxx.blog.service.security;

import com.zxx.blog.entity.security.User;

public interface UserService {
    User findById(String objectId);

    User findByName(String userName);

    void save(User user);

    void delete(String objectId);
}
