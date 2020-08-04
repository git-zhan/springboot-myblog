package com.myblog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyBlogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBlogWebApplication.class,args);
    }
}
