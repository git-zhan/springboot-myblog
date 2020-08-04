package com.myblog.exception;

public class ClientException extends RuntimeException{
    public static final String USER_NOT_EXIST = "user not exist!";

    public static final String PARAM_NAME_NOT_EXIST = "param name not exist!";

    public static final String PARAM_NOT_EXIST = "param not exist!";

    public ClientException(String message){
        super(message);
    }
}
