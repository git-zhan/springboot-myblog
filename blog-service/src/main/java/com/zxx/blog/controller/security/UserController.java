package com.zxx.blog.controller.security;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/hello1")
    public String hello(){
        return "hello";
    }

//    @RequestMapping("/login")
//    public String login(){
//        return "login";
//    }
}
