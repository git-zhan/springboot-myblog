package com.zxx.blog.service.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.RoleList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxx.blog.config.common.WebUtils;
import com.zxx.blog.constant.SystemFinal;
import com.zxx.blog.entity.security.Authority;
import com.zxx.blog.entity.security.Role;
import com.zxx.blog.mapper.security.AuthorityMapper;
import com.zxx.blog.model.exception.ClientException;
import com.zxx.blog.model.request.Response;
import com.zxx.blog.service.security.AuthorityService;
import com.zxx.blog.service.security.RoleService;
import com.zxx.blog.util.data.CodeUtils;
import com.zxx.blog.util.data.ListUtils;
import com.zxx.blog.util.data.LocalDateTimeUtils;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	private AuthorityMapper mapper;
	
	
	@Autowired
	private RoleService roleService;
	/**
	 * 获取所有权限树
	 */
	@Override
	public List<Authority> getAuthorityTree(Authority authority) {
		return getAuthorityTree(authority, null);
	}
	
	/** 获取权限树根据被选中的权限
	 *       
	 * @param authority
	 * @return
	 */
	public List<Authority> getAuthorityTree(Authority authority,Collection<String> checkedList) {
		List<Authority> topList = getChildListByParentId(authority.getObjectId());
		if(ListUtils.isNotBlank(topList)) {
			if(ListUtils.isBlank(checkedList)) {
				authority.setChildren(topList);
			}else {
				authority.setChildren(ListUtils.filterAsList(topList, ele -> checkedList.contains(ele.getObjectId())));
			}
			for(Authority auth : topList) {
				getAuthorityTree(auth);
			}
		}
		return authority.getChildren();
	}
	
	/**
	 * 根据权限名称获取权限
	 * @param name
	 * @return
	 */
	public Authority getAuthorityByName(String name) {
		return mapper.selectByName(name);
	}
	
	/**
	 * 根据Id获取权限
	 * @param name
	 * @return
	 */
	public Authority getAuthorityById(String id) {
		return mapper.selectById(id);
	}
	/**
	 * 根据查询条件获取权限列表
	 */
	@Override
	public List<Authority> getAuthorityListByQuery(Map<String,Object> query) {
		try {
			return mapper.selectByQuery(query);
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 获取父节点下所有的子节点
	 */
	@Override
	public List<Authority> getChildListByParentId(String id) {
		try {
			Map<String,Object> query = new HashMap<String, Object>();
			query.put("parentId", id);
			return getAuthorityListByQuery(query);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 节点下所有的节点
	 */
	@Override
	public List<Authority> getAllChildList(List<Authority> authList,String id) {
		try {
			List<Authority> childList = getChildListByParentId(id);
			if(ListUtils.isNotBlank(authList)) {
				authList.addAll(childList);
				for(Authority auth : authList) {
					getAllChildList(authList, auth.getObjectId());
				}
			}
			return authList;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 保存权限
	 */
	@Transactional
	@Override
	public Response save(Authority authority) {
		try {
			if(authority.getObjectId() == null) {
				authority.setObjectId(CodeUtils.getUUID());
				if(getAuthorityByName(authority.getName()) != null) {
					return Response.error("权限名称：" +  authority.getName() + "已经存在!");
				}
				authority.setCreateTime(LocalDateTimeUtils.getNowTimestamp());
				mapper.insert(authority);
			}else {
				authority.setUpdateTime(LocalDateTimeUtils.getNowTimestamp());
				mapper.update(authority);
			}
			return Response.success();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 删除权限
	 */
	@Transactional
	@Override
	public Response deleteAuthority(String id) {
		try {
			Authority topAuth = getAuthorityById(id);
			if(topAuth != null && (SystemFinal.FLAG_NO + "").equals(topAuth.getParentId())) {
				List<Authority> parentList = getChildListByParentId(SystemFinal.FLAG_NO + "");
				if(ListUtils.isNotBlank(parentList) && parentList.size() == 1) {
					return Response.error("至少存在一个顶级权限!");
				}
			}
			List<Authority> authList = getAllChildList(new ArrayList<Authority>(), id);
			authList.add(getAuthorityById(id));
			for(Authority auth : authList) {
				mapper.delete(auth.getObjectId());
			}
			return Response.success();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/** 查询角色的权限列表
	 * @param objectId 角色主键
	 */
	@Override
	public Map<String,Object> getRoleAuthTree(String roleId) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Authority> roleAuthList = mapper.getRoleAuthList(roleId);
		data.put("treeData", getAuthorityTree(new Authority(SystemFinal.STR_FLAG_NO)));
		data.put("checkedKeys", ListUtils.filter(roleAuthList, ele -> ele.getRoleId() != null).map(ele -> ele.getObjectId()).collect(Collectors.toList()));
		return data;
	}
	
	/**
	 * 获取用户权限信息
	 */
	@Override
	public List<Authority> getUserAuthTree() {
		Set<String> checkedAuthList = new HashSet<String>();
		List<Authority> roleAuthList = null;
		List<Role> roleList = roleService.getRoleListByUser(WebUtils.getUserId());
		if(ListUtils.isBlank(roleList)) {
			throw new ClientException("用户没有设置角色!");
		}else {
			for(Role role : roleList) {
				roleAuthList = mapper.getRoleAuthList(role.getObjectId());
				checkedAuthList.addAll(ListUtils.filter(roleAuthList, ele -> ele.getRoleId() != null).map(ele -> ele.getObjectId()).collect(Collectors.toSet()));
			}
		}
		if(ListUtils.isBlank(checkedAuthList)) {
			throw new ClientException("用户角色没有设置权限!");
		}
		return getAuthorityTree(new Authority(SystemFinal.STR_FLAG_NO), checkedAuthList);
	}

	
}
