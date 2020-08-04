package com.myblog.model;

public enum Error {

    USER_NOT_LOGIN(1001,"用户未登录!"),
    USER_NOT_FOUND(1002,"用户名不存在或者密码错误!");

    private int errorCode;

    private String errorMessage;

    Error(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String geterrorMessage() {
        return errorMessage;
    }

    public void seterrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
