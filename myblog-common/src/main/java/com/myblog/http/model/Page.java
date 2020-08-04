package com.myblog.http.model;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    private Integer pageNo;
    private Integer pageSize;
    private List<Object> rows;
    private Integer total;
}
