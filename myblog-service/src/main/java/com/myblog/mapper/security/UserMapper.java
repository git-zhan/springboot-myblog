package com.myblog.mapper.security;

import com.myblog.entity.security.SecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    SecurityUser findById(Integer objectId);

    SecurityUser findByName(String userName);

    Integer insert(SecurityUser user);

    Integer update(SecurityUser user);

    Integer delete(Integer objectId);

    List<SecurityUser> findAll();

    List<SecurityUser> findByPage();
}
