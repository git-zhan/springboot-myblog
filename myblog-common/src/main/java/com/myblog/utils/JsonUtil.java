package com.myblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myblog.exception.ClientException;

public class JsonUtil {

    public static <T>T strToModel(String string,Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        T object = null;
        try {
            object = mapper.readValue(string,clazz);
        } catch (JsonProcessingException e) {
            throw new ClientException("string parse model error!");
        }
        return object;
    }
}
