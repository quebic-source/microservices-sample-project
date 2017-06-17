package com.quebic.common.response;

public class CommonResponse {

	private int status;
	private Object message;
	
	public CommonResponse(int status, Object message) {
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
	
}
