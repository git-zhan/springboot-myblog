package com.zxx.blog.entity.security;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Authority {
	private String objectId;
	private String name;
	private String description;
	private Boolean isActive;
	private String path;
	private Integer sequence;
	private String parentId;
	private Long updateTime;
	private String updateBy;
	private Long createTime;
	private String createBy;
	
	private String roleId;
	private List<Authority> children;
	
	public Authority(String objectId) {
		this.objectId = objectId;
	}
}
