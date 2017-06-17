package com.quebic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Duplicate Email Registered")  //403
public class DuplicateEmailRegisteredException extends RuntimeException{

	private static final long serialVersionUID = 4333940983178070455L;
	
	public DuplicateEmailRegisteredException() {
	}
	
	public DuplicateEmailRegisteredException(String message) {
		super(message);
	}

}
