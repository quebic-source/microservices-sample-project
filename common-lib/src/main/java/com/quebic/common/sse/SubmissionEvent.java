package com.quebic.common.sse;

public class SubmissionEvent {

	private String key;
	private Object message;
	
	public SubmissionEvent(ListenerType type , String id, Object message) {
		this.key = type.prepareKey(id);
		this.message = message;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	
	
}
