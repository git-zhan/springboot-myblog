package com.zxx.blog.entity.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleAuthority {
	private String objectId;
	private String roleId;
	private String authorityId;
}
