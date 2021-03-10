package com.zxx.blog.entity.security;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
    private String objectId;
    private String name;
    private String description;
    private List<Authority> authList;
}
