package com.quebic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)  //400
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -6659322898478118584L;

	public BadRequestException() {
		this("Bad Request");
	}
	
	public BadRequestException(String message) {
		super(message);
	}
	
}
