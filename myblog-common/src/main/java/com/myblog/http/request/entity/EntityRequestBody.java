package com.myblog.http.request.entity;

import com.myblog.http.request.RequestBody;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EntityRequestBody<T> extends RequestBody {
    private T entity;

    public EntityRequestBody(T t) {
        this.entity = t;
    }
}
