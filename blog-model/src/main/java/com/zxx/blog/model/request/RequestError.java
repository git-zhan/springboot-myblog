package com.zxx.blog.model.request;



public enum  RequestError {
    
    TOKEN_EXPIRED(401,"未生成Token或者Token已失效!"),
    USERNAME_ERROR(1001,"用户名错误!"),
    PASSWORD_ERROR(1002,"密码错误!"),
    ACCOUNT_LOCKED_ERROR(1003,"账号被锁定!"),
	AUTHOUTHRITY_ERROR(1111,"没有权限访问!");

    private int status;
    private String message;
    
	private RequestError(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
}
