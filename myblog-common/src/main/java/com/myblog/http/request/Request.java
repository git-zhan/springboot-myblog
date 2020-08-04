package com.myblog.http.request;

import com.myblog.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Request {
    public static final String REQUEST_FIELD_HEADER = "header";
    public static final String REQUEST_FIELD_BODY = "body";
    public static final String REQUEST_FIELD_BODY_ENTITY = "entity";
    public static final String REQUEST_FIELD_BODY_ENTITYLIST = "entityList";

    private RequestHeader header;

    private RequestBody body;
}
