package com.myblog.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static <T> Boolean isBlank(List<T> list){
        return list == null || list.size() == 0;
    }

    public static <T> Boolean isNotBlank(List<T> list){
        return !isBlank(list);
    }

    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }
}
