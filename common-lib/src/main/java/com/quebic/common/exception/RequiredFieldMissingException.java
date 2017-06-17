package com.quebic.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)  //400
public class RequiredFieldMissingException extends RuntimeException{

	private static final long serialVersionUID = 1537589278610015552L;
	
	public RequiredFieldMissingException(Object... fields) {
		super(String.format("Required Field Missing [%s]", fields));
	}
	
}
