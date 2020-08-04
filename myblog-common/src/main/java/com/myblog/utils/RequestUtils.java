package com.myblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myblog.exception.ClientException;
import com.myblog.http.request.Request;
import com.myblog.http.request.RequestHeader;
import com.myblog.http.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUtils {
    private static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static RequestHeader getHeader(JsonNode headerNode){
        ObjectMapper mapper = new ObjectMapper();
        try {
            logRequest(headerNode.toString());
            return mapper.readValue(headerNode.toString(),RequestHeader.class);
        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage());
        }
    }

    public static <T> T  getEntity(JsonNode bodyNode,Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode entityNode = mapper.readTree(bodyNode.get(Request.REQUEST_FIELD_BODY_ENTITY).toString());
            logRequest(entityNode.toString());
            return mapper.readValue(entityNode.toString(),clazz);
        } catch (JsonProcessingException e) {
            throw new ClientException(e.getMessage());
        }
    }

    public static void logRequest(String request){
        if(logger.isDebugEnabled()){
            logger.debug(request);
        }
    }
}
