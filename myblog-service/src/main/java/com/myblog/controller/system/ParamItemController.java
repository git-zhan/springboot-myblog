package com.myblog.controller.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.myblog.controller.common.BaseController;
import com.myblog.entity.system.ParamItem;
import com.myblog.http.response.Response;
import com.myblog.http.response.PageResponseBody;
import com.myblog.service.system.ParamItemService;
import com.myblog.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParamItemController extends BaseController {

    @Autowired
    private ParamItemService paramItemService;

    @GetMapping("/paramItem/getParamItemList/{objectId}")
    public Response getParamItemList(@PathVariable("objectId") Integer paramId) {
        Response response = new Response();
        List<ParamItem> list = paramItemService.getParamItemList(paramId);
        PageResponseBody body = new PageResponseBody();
        body.setRows(list);
        response.setBody(body);
        return ResponseUtils.success(response);
    }

    @RequestMapping("/paramItem/saveParamItemList")
    public Response test(@RequestBody JsonNode jsonNode){
        List<ParamItem> list = getEntityList(jsonNode,ParamItem.class);
        return new Response();
    }
}
