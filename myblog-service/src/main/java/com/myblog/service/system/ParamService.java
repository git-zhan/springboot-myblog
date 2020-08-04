package com.myblog.service.system;

import com.myblog.entity.system.Param;

import java.util.List;

public interface ParamService {
    Param findById(Integer objectId);
    Param findByName(String name);
    List<Param> findByType(String type);
    Integer saveParam(Param param);
}
