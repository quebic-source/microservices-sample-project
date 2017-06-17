package com.quebic.common.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason="ListenerType Not Found")  //500
public class ListenerTypeNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6747852635122018488L;

}
