package com.myblog.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ResponseHeader {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long transactionId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int errorCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
