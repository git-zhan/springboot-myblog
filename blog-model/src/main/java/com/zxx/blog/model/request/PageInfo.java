package com.zxx.blog.model.request;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageInfo {
	private int pageNo;
	
	private int pageSize;
	
	private List<?> rows;
	
	private long total;
	
	private Map<String,Object> query;
	
	public static <T> Page<T> startPage(PageInfo pageInfo) {
		return PageHelper.startPage(pageInfo.getPageNo(),pageInfo.getPageSize());
	}
}
