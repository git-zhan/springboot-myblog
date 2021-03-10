package com.zxx.blog.config.common;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zxx.blog.util.data.JSONUtils;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class WebReuqestLogAspect {
	
	@Pointcut("execution(* com.zxx.blog.controller..*.*(..))")
	public void requestLog() {}
	
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        try {
        	log.info("Request Args   : {}", JSONUtils.objToStr(joinPoint.getArgs()));
		} catch (Exception e) {
			log.info("Request Args   : null");
		}
        //打印結束
        log.info("========================================== End ==========================================");
    }
    
    @AfterReturning(returning = "resp", pointcut = "requestLog()")
    public void doAfterReturning(Object resp) throws Throwable {
    	log.info("========================================== Start ==========================================");
		log.info(JSONUtils.objToStr(resp));
		log.info("========================================== End ==========================================");
    }
}

