package com.myblog.mapper.system;

import com.myblog.entity.system.ParamItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParamItemMapper {
    List<ParamItem> getParamItemList(Integer paramId);
    Integer addParamItem(ParamItem paramItem);
}
