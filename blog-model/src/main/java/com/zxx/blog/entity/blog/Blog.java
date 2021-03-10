package com.zxx.blog.entity.blog;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Blog {
	private String objectId;
	private String title;
	private String label;
	private String content;
	private String status;
	private String userId;
	private Long updateTime;
	private String updateBy;
	private Long createTime;
	private String createBy;
}
