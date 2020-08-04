package com.myblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myblog.exception.ClientException;
import com.myblog.http.response.Response;
import com.myblog.http.response.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功!";
    public static final String DEFAULT_ERROR_MESSAGE = "操作错误!";
    public static final String SUCCESS_STATUS = "success";
    public static final String ERROR_STATUS = "error";

    public static Response success(Response response,String message){
        response.getHeader().setStatus(SUCCESS_STATUS);
        response.getHeader().setMessage(message);
        logResponse(response);
        return response;
    }

    public static Response success(Response response) {
        response.getHeader().setStatus(SUCCESS_STATUS);
        response.getHeader().setMessage(DEFAULT_SUCCESS_MESSAGE);
        logResponse(response);
        return response;
    }

    public static Response error(Response response) {
        response.getHeader().setStatus(ERROR_STATUS);
        response.getHeader().setMessage(DEFAULT_ERROR_MESSAGE);
        logResponse(response);
        return response;
    }

    public static Response error(Response response,String message) {
        response.getHeader().setStatus(ERROR_STATUS);
        response.getHeader().setMessage(message);
        logResponse(response);
        return response;
    }

    public static void logResponse(Response response){
        if(logger.isDebugEnabled()){
            try {
                logger.debug(mapper.writeValueAsString(response));
            } catch (JsonProcessingException e) {
                throw new ClientException(e.getMessage());
            }
        }
    }
}
