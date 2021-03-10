package com.zxx.blog.mapper.security;


import com.zxx.blog.entity.security.User;



public interface UserMapper {
    User findById(String objectId);

    User findByName(String userName);

    int insert(User user);

    int update(User user);

    int delete(String objectId);
}
