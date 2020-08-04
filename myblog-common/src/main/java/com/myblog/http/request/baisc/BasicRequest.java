package com.myblog.http.request.baisc;

import com.myblog.http.request.Request;
import com.myblog.http.request.RequestHeader;
import lombok.Data;

@Data
public class BasicRequest extends Request {
    private RequestHeader header;

    private BasicRequestBody body;

}
