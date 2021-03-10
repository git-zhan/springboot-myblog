package com.zxx.blog.config.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zxx.blog.model.exception.ClientException;
import com.zxx.blog.model.request.Response;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    
    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e){
    	log.error(e.getMessage(),e);
        return Response.error(e.getMessage());
    }
    
    @ExceptionHandler(ClientException.class)
    public Object handlerClientException(ClientException e){
    	log.error(e.getMessage(),e);
    	if(e.getError() != null) {
    		return Response.error(e.getError());
    	}else {
    		return Response.error(e.getMessage());
    	}
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(),e);
        return Response.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
