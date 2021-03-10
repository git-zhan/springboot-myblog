package com.zxx.blog.util.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static <T> T strToObj(String str,Class<T> tClass) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(str,tClass);
    }
    
    public static String objToStr(Object obj) throws JsonProcessingException {
    	return objectMapper.writeValueAsString(obj);
    }
}
