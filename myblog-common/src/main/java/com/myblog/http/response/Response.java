package com.myblog.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myblog.exception.ClientException;
import com.myblog.http.request.RequestHeader;
import com.myblog.model.Error;
import com.myblog.model.HttpConst;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class Response {
    private ResponseHeader header;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResponseBody body;

    public Response(RequestHeader header){
        this.setHeader(new ResponseHeader());
        this.getHeader().setTransactionId(header.getTransactionId());
    }

    public Response(){
        this.setHeader(new ResponseHeader());
    }


    public Response(ClientException ce) {
        ResponseHeader header = new ResponseHeader();
        header.setStatus("error");
        header.setMessage(ce.getMessage());
        header.setTransactionId(new Date().getTime());
        this.setHeader(header);
    }

    public Response(Error error){
        ResponseHeader header = new ResponseHeader();
        header.setStatus(HttpConst.RESPONSE_STATUS_ERROR);
        header.setTransactionId(new Date().getTime());
        header.setErrorCode(error.geterrorCode());
        header.setMessage(error.geterrorMessage());
        this.setHeader(header);
    }
}
