package com.zxx.blog.config.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${upload.savePath}")
	private String savePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//拦截/**请求映射到外部资源路径下
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + savePath);
	}
}
