package com.myblog.controller.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.myblog.entity.system.Param;
import com.myblog.entity.system.ParamItem;
import com.myblog.http.request.Request;
import com.myblog.http.request.RequestHeader;
import com.myblog.utils.JsonUtil;
import com.myblog.utils.ListUtils;
import com.myblog.utils.RequestUtils;

import java.util.List;

public class BaseController {
    protected RequestHeader getHeader(JsonNode jsonNode) {
        return RequestUtils.getHeader(jsonNode.get(Request.REQUEST_FIELD_HEADER));
    }
    protected <T> T getEntity(JsonNode jsonNode,Class<T> clazz){
        return RequestUtils.getEntity(jsonNode.get(Request.REQUEST_FIELD_BODY), clazz);
    }

    protected <T> List<T> getEntityList(JsonNode jsonNode, Class<T> clazz) {
        JsonNode bodyNode = jsonNode.get(Request.REQUEST_FIELD_BODY);
        JsonNode entityListNode = bodyNode.get(Request.REQUEST_FIELD_BODY_ENTITYLIST);
        List<T> list = ListUtils.newList();
        if(entityListNode.isArray()){
            for(JsonNode paramItemNode : entityListNode){
                T t = JsonUtil.strToModel(jsonNode.toString(),clazz);
                list.add(t);
            }
        }
        return list;
    }
}
