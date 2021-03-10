package com.zxx.blog.controller.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxx.blog.entity.security.Role;
import com.zxx.blog.model.interfaces.IDRequest;
import com.zxx.blog.model.request.PageInfo;
import com.zxx.blog.model.request.Response;
import com.zxx.blog.service.security.RoleService;

@RestController
@RequestMapping("/blog/auth/security/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	//创建或者更新角色
	@RequestMapping("/save")
	public Response save(@RequestBody Role role) {
		try {
			return roleService.save(role);
		} catch (Exception e) {
			throw e;
		}
	}
	//分页查询角色
	@RequestMapping("/getRoleListByPage")
	public Response getRoleListByPage(@RequestBody PageInfo page) {
		try {
			return roleService.getRoleListByPage(page);
		} catch (Exception e) {
			throw e;
		}
	}
	
	//删除角色
	@RequestMapping("/deleteRole")
	public Response deleteRole(@Valid @RequestBody IDRequest idRequest) {
		return roleService.deleteRole(idRequest.getIds());
	}
	
}
