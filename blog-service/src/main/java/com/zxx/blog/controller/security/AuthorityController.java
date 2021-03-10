package com.zxx.blog.controller.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxx.blog.constant.SystemFinal;
import com.zxx.blog.entity.security.Authority;
import com.zxx.blog.entity.security.Role;
import com.zxx.blog.model.request.Response;
import com.zxx.blog.service.security.AuthorityService;

@RestController
@RequestMapping("/blog/auth/security/auth")
public class AuthorityController {
	
	@Autowired
	private AuthorityService service;
	
	//获取权限树
	@RequestMapping("/getAuthorityTree")
	public Response getAuthorityTree() {
		return Response.success(service.getAuthorityTree(new Authority(SystemFinal.STR_FLAG_NO)));
	}
	
	//通过查询条件获取权限列表
	@RequestMapping("/getAuthorityListByQuery")
	public Response getAuthorityListByQuery(@RequestBody Map<String,Object> query) {
		return Response.success(service.getAuthorityListByQuery(query));
	}
	
	//创建或者更新权限
	@RequestMapping("/save")
	public Response save(@RequestBody Authority authority) {
		return service.save(authority);
	}
	
	//删除权限
	@RequestMapping("/deleteAuthority")
	public Response deleteAuthority(@RequestBody Authority authority) {
		return service.deleteAuthority(authority.getObjectId());
	}
	
	//获取角色权限树
	@RequestMapping("/getRoleAuthTree")
	public Response getRoleAuthTree(@RequestBody Role role) {
		return Response.success(service.getRoleAuthTree(role.getObjectId()));
	}
	
	//获取用户权限树
	@RequestMapping("/getUserAuthTree")
	public Response getUserAuthTree() {
		return Response.success(service.getUserAuthTree());
	}
}
