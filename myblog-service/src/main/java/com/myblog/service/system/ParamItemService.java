package com.myblog.service.system;

import com.myblog.entity.system.ParamItem;

import java.util.List;

public interface ParamItemService {
    ParamItem findById(Integer objectId);
    List<ParamItem> getParamItemList(Integer paramId);
    List<ParamItem> getParamItemList(Integer paramId,Boolean isThrowException);
    Integer addParamItem(ParamItem paramItem);
    void saveParamItemList(List<ParamItem> paramItemList);
}
