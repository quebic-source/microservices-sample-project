package com.quebic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Duplicate Store Registered")  //403
public class DuplicateStoreRegisteredException extends RuntimeException{

	private static final long serialVersionUID = 4333940983178070455L;
	
	public DuplicateStoreRegisteredException() {
	}
	
	public DuplicateStoreRegisteredException(String message) {
		super(message);
	}

}
