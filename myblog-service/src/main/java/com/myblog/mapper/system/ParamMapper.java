package com.myblog.mapper.system;

import com.myblog.entity.system.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParamMapper {
    Param findById(Integer objectId);
    Param findByName(String name);
    List<Param> findByType(String type);
    Integer saveParam(Param param);
}
