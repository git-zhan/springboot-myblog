package com.zxx.blog.mapper.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zxx.blog.entity.security.Authority;

public interface AuthorityMapper {
	
	List<Authority> selectByQuery(@Param("query") Map<String,Object> query);

	Authority selectByName(String name);

	void insert(Authority authority);

	void update(Authority authority);

	Authority selectById(String id);

	void delete(String id);
	
	List<Authority> getRoleAuthList(String id);
}
