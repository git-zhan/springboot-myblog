package com.myblog.entity.system;

import com.myblog.model.Basic;
import com.myblog.model.BasicActive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Param extends BasicActive implements Serializable {
    private static final String PARAM_SYS = "System";
    private static final String PARAM_USER = "User";
    private String name;
    private String description;
    private String type;
}
