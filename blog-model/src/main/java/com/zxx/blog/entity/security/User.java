package com.zxx.blog.entity.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    private String objectId;
    private boolean isActive;
    private String userName;
    private String password;
    private String description;
    private String sex;
    private String address;
    private Date birth;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
}
