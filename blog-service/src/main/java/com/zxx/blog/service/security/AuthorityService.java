package com.zxx.blog.service.security;

import java.util.List;
import java.util.Map;

import com.zxx.blog.entity.security.Authority;
import com.zxx.blog.model.request.Response;

public interface AuthorityService {

	List<Authority> getAuthorityListByQuery(Map<String, Object> query);

	Response save(Authority authority);

	Response deleteAuthority(String id);
	
	List<Authority> getAllChildList(List<Authority> authList, String id);

	List<Authority> getChildListByParentId(String id);
	
	List<Authority> getAuthorityTree(Authority authority);

	/** 查询角色的权限列表
	 * @param objectId 角色主键
	 */
	Map<String, Object> getRoleAuthTree(String roleId);

	List<Authority> getUserAuthTree();
}
