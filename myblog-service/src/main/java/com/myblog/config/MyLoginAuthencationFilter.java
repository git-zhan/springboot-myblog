//package com.myblog.config;
//
//
//import com.myblog.exception.ClientException;
//import com.myblog.model.HttpConst;
//import com.myblog.utils.JsonUtil;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
//@Component
//public class MyLoginAuthencationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        if(!HttpConst.REQUEST_METHOD_POST.equals(request.getMethod())) {
//            throw new ClientException("Not support request method!");
//        }
//        if(MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType()) || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(request.getContentType())) {
//            String userName = null;
//            String password = null;
//            try {
//                Map<String,String> map = JsonUtil.inputstramToMap(request.getInputStream());
//                userName = map.get(HttpConst.LOGIN_USER_NAME);
//                password = map.get(HttpConst.LOGIN_PASSWORD);
//            } catch (IOException e) {
//                throw new ClientException(e);
//            }
//        }
//        return super.attemptAuthentication(request,response);
//    }
//}
