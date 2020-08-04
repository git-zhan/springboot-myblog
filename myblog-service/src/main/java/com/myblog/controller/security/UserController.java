package com.myblog.controller.security;

import com.myblog.entity.security.SecurityUser;
import com.myblog.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/security/user/{id}")
    public SecurityUser test(@PathVariable("id") Integer objectId){
        return userService.findById(objectId);
    }


    @PostMapping("/security/getUserListByForm")
    public List<SecurityUser> test(SecurityUser user){
        List<SecurityUser> list = new ArrayList<SecurityUser>();
        list.add(user);
        return list;
    }
}
