package com.myblog.controller.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.myblog.controller.common.BaseController;
import com.myblog.entity.system.Param;
import com.myblog.http.response.Response;
import com.myblog.http.response.PageResponseBody;
import com.myblog.service.system.ParamService;
import com.myblog.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParamController extends BaseController {

    @Autowired
    private ParamService paramService;

    @PostMapping("/param/saveParam")
    public Response saveParam(@RequestBody JsonNode jsonNode) {
        Response response = new Response(getHeader(jsonNode));
        Param param = getEntity(jsonNode,Param.class);
        if(paramService.saveParam(param) > 0) {
            response = ResponseUtils.success(response);
        }else{
            response = ResponseUtils.error(response);
        }
        return response;
    }

    @PostMapping("/param/getParamList")
    public Response getParamList(@RequestBody JsonNode jsonNode){
        Response response = new Response(getHeader(jsonNode));
        Param param = getEntity(jsonNode,Param.class);
        List<Param> paramList = paramService.findByType(param.getType());
        PageResponseBody body = new PageResponseBody();
        body.setRows(paramList);
        response.setBody(body);
        return response;
    }
}
