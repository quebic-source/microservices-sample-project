package com.quebic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Old Password Not Match")  //400
public class OldPasswordNotMatch extends RuntimeException{

	private static final long serialVersionUID = -7106944187406637244L;

	public OldPasswordNotMatch() {
	}
	
	public OldPasswordNotMatch(String message) {
		super(message);
	}
	
}
