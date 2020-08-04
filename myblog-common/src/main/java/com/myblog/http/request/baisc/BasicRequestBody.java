package com.myblog.http.request.baisc;

import com.myblog.http.request.RequestBody;
import lombok.Data;

@Data
public class BasicRequestBody<T> extends RequestBody {
    private T entity;
}
