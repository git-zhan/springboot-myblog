package com.myblog.http.request.entity;

import com.myblog.http.request.Request;
import com.myblog.http.request.RequestHeader;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntityRequest<T> extends Request {
    private RequestHeader header;

    private EntityRequestBody<T> body;

    public EntityRequestBody<T> getBody() {
        return body;
    }
}
