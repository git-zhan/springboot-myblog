package com.myblog.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myblog.http.response.ResponseBody;
import lombok.Data;

import java.util.List;

@Data
public class PageResponseBody extends ResponseBody {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<?> rows;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;
}
