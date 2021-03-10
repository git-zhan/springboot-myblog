package com.zxx.blog.service.security;

import java.util.List;


import com.zxx.blog.entity.security.Role;
import com.zxx.blog.entity.security.RoleAuthority;
import com.zxx.blog.model.request.PageInfo;
import com.zxx.blog.model.request.Response;


public interface RoleService {
	List<Role> getRoleListByUser(String userId);

	Response save(Role role);

	Response getRoleListByPage(PageInfo page);

	Response deleteRole(List<String> ids);

	Boolean saveRoleAuth(RoleAuthority roleAuth);

	/**
	 * 删除用户角色
	 */
	Boolean deleteRoleAuth(String roleId);
}
