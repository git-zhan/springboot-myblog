package com.zxx.blog.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	private static final int ERROR_STATUS = -1;
	
	private int status;
	
	@JsonInclude(value = Include.NON_NULL)
	private Object data;
	
	private String message;
	
	public Response(RequestError error) {
		this.status = error.getStatus();
		this.message = error.getMessage();
	}
	
	public static Response success(Object data) {
		return success(data, "success");
	}
	
	public static Response success() {
		return success(null,"success");
	}
	
	public static Response success(Object data,String message) {
		return new Response(0, data, message);
	}
	
	public static Response error(RequestError error) {
		return new Response(error);
	}
	
	public static Response error(String message) {
		return new Response(ERROR_STATUS,null,message);
	}
}
