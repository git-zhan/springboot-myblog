package com.zxx.blog.service.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zxx.blog.entity.security.Authority;
import com.zxx.blog.entity.security.Role;
import com.zxx.blog.entity.security.RoleAuthority;
import com.zxx.blog.mapper.security.RoleMapper;
import com.zxx.blog.model.request.PageInfo;
import com.zxx.blog.model.request.Response;
import com.zxx.blog.service.security.RoleService;
import com.zxx.blog.util.data.CodeUtils;



@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 保存用户角色权限
	 */
	@Transactional
	@Override
	public Boolean saveRoleAuth(RoleAuthority roleAuth) {
		return roleMapper.insertRoleAuth(roleAuth) > 0;
	}
	
	/**
	 * 删除用户角色权限
	 */
	@Transactional
	@Override
	public Boolean deleteRoleAuth(String roleId) {
		return roleMapper.deleteRoleAuth(roleId) > 0;
	}
	/**
	 * 获取用户所有角色列表
	 */
	@Override
	public List<Role> getRoleListByUser(String userId) {
		List<Role> role = null;
		try {
			role = roleMapper.getRoleListByUser(userId);
		} catch (Exception e) {
			throw e;
		}
		return role;
	}
	
	public Role getRoleByName(String name) {
		return roleMapper.getRoleByName(name);
	}
	
	/**
	 * 保存角色
	 */
	@Override
	@Transactional
	public Response save(Role role){
		try {
			if(role.getObjectId() == null) {
				if(getRoleByName(role.getName()) != null) {
					return Response.error("角色名称" + role.getName() + "已经存在!");
				}
				role.setObjectId(CodeUtils.getUUID());
				roleMapper.insert(role);
			}else {
				roleMapper.update(role);
				deleteRoleAuth(role.getObjectId());
			}
			RoleAuthority roleAuth = null;
			for(Authority auth : role.getAuthList()) {
				roleAuth = new RoleAuthority();
				roleAuth.setObjectId(CodeUtils.getUUID());
				roleAuth.setRoleId(role.getObjectId());
				roleAuth.setAuthorityId(auth.getObjectId());
				saveRoleAuth(roleAuth);
			}
			return Response.success();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Response getRoleListByPage(PageInfo pageInfo) {
		try {
			return Response.success(getPageInfo(pageInfo));
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 调用分页插件完成分页
	 * @param page
	 * @return
	 */
    private PageInfo getPageInfo(PageInfo pageInfo) {
        Page<Role> page = PageHelper.startPage(pageInfo.getPageNo(), pageInfo.getPageSize());
        pageInfo.setRows(roleMapper.selectPage(pageInfo.getQuery()));
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
    
    @Transactional
	@Override
	public Response deleteRole(List<String> ids) {
		try {
			for(String id : ids) {
				roleMapper.delete(id);
				roleMapper.deleteRoleAuth(id);
			}
			return Response.success();
		} catch (Exception e) {
			throw e;
		}
	}
}
