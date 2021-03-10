package com.zxx.blog.model.exception;

import com.zxx.blog.model.request.RequestError;


public class ClientException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RequestError error;
	
	public RequestError getError() {
		return error;
	}

	public void setError(RequestError error) {
		this.error = error;
	}

	public ClientException() {
		super();
	}
	
	public ClientException(String message) {
		super(message);
	}
	
	public ClientException(Throwable throwable) {
		super(throwable);
	}
	
	public ClientException(String message,Throwable throwable) {
		super(message, throwable);
	}
	
	public ClientException(RequestError error) {
		super(error.getMessage());
		this.error = error;
	}
}
