package com.zxx.blog.util.data;


public class StringUtils {

    public static Boolean isBlank(String str) {
        return str == null || str.trim() == "";
    }

    public static Boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
//    public static Double getCalResult(String expression) {
//    	
//    }
}
