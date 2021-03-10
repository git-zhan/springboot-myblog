package com.zxx.blog.util.data;

import java.util.UUID;

public class CodeUtils {
	
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
