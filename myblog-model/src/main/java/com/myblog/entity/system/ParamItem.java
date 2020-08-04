package com.myblog.entity.system;


import com.myblog.model.Basic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParamItem extends Basic {
    private String labelZh;
    private String value;
    private String label;
    private String description;
    private Integer paramId;
}
