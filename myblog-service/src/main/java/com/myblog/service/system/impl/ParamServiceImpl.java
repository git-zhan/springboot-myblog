package com.myblog.service.system.impl;

import com.myblog.entity.system.Param;
import com.myblog.exception.ClientException;
import com.myblog.mapper.system.ParamMapper;
import com.myblog.service.system.ParamService;
import com.myblog.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParamServiceImpl implements ParamService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ParamMapper paramMapper;

    @Override
    public Param findById(Integer objectId) {
        Param param = null;
        try {
            param = paramMapper.findById(objectId);
        } catch (ClientException ce) {
            throw new ClientException(ClientException.PARAM_NAME_NOT_EXIST);
        }
        return param;
    }

    @Override
    public Param findByName(String name) {
        Param param = null;
        try {
            param = paramMapper.findByName(name);
        } catch (ClientException ce) {
            throw new ClientException(ClientException.PARAM_NAME_NOT_EXIST);
        }
        return param;
    }

    @Override
    public List<Param> findByType(String type) {
        List<Param> paramList = null;
        paramList = paramMapper.findByType(type);
        if(ListUtils.isNotBlank(paramList)) {
            return paramList;
        }else{
            throw new ClientException(ClientException.PARAM_NAME_NOT_EXIST);
        }
    }

    @Override
    @Transactional
    public Integer saveParam(Param param) {
        int flag = -1;
        try {
            Param checkparam = paramMapper.findByName(param.getName());
            if(checkparam == null) {
                if(param.getObjectId() == null) {
                    flag = paramMapper.saveParam(param);
                }
            }else{
                throw new ClientException("参数已经存在!");
            }
        } catch (ClientException ce) {
            throw new ClientException(ce.getMessage());
        }
        return flag;
    }
}
