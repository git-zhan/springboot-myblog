package com.zxx.blog.mapper.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxx.blog.entity.security.Role;
import com.zxx.blog.entity.security.RoleAuthority;

public interface RoleMapper {
	
	 List<Role> getRoleListByUser(String userId);
	 
	 Integer insert(Role role);
	 
	 Integer update(Role role);
	 
	 List<Role> selectAll();
	 
	 List<Role> selectPage(@Param("query") Map<String,Object> query);

	 Integer delete(String id);

	 Role getRoleByName(String name);

	 int insertRoleAuth(RoleAuthority roleAuth);

	int deleteRoleAuth(String roleId);
}
