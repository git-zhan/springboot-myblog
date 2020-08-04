package com.myblog.controller.common;

import com.myblog.exception.ClientException;
import com.myblog.http.response.Response;
import com.myblog.utils.ResponseUtils;
import com.myblog.utils.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public Response handleClientException(ClientException ce){
        Response response = new Response(ce);
        ResponseUtils.logResponse(response);
        ce.printStackTrace();
        return response;
    }
}
