package com.myblog.service.system.impl;

import com.myblog.entity.system.ParamItem;
import com.myblog.exception.ClientException;
import com.myblog.mapper.system.ParamItemMapper;
import com.myblog.service.system.ParamItemService;
import com.myblog.utils.ListUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParamItemServiceImpl implements ParamItemService {

    @Resource
    private ParamItemMapper paramItemMapper;

    @Override
    public ParamItem findById(Integer objectId) {
        return null;
    }

    @Override
    public List<ParamItem> getParamItemList(Integer paramId) {
        return getParamItemList(paramId,false);
    }

    @Override
    public List<ParamItem> getParamItemList(Integer paramId, Boolean isThrowException) {
        List<ParamItem> list = paramItemMapper.getParamItemList(paramId);
        if(ListUtils.isBlank(list) && isThrowException){
            throw new ClientException("paramItem is not exist!");
        }else{
            list = list == null ? ListUtils.newList() : list;
        }
        return list;
    }

    @Override
    public Integer addParamItem(ParamItem paramItem) {
        Integer flag = paramItemMapper.addParamItem(paramItem);
        if(flag <= 0) {
            throw new ClientException("save paramItem error!");
        }
        return flag;
    }

    @Override
    public void saveParamItemList(List<ParamItem> paramItemList) {
        for(ParamItem paramItem : paramItemList) {
            addParamItem(paramItem);
        }
    }
}
