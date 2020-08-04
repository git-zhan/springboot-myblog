package com.myblog.http.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestHeader {
    private Long transactionId;
    private String userName;
    private String language;
}
