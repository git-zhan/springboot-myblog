package com.myblog.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class StringUtils {

    public static Boolean isBlank(String string){
        if(string == null) {
            return false;
        }else if("".equals(string.trim())){
            return false;
        }
        return true;
    }

    public static  Boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}
