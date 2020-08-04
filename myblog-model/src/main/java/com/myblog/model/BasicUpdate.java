package com.myblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicUpdate extends Basic implements Serializable {
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    public void create(String userName){
        this.setCreateBy(userName);
        this.setCreateTime(new Date());
        this.update(userName);
    }

    public void update(String userName){
        this.setUpdateBy(userName);
        this.setUpdateTime(new Date());
    }

}
