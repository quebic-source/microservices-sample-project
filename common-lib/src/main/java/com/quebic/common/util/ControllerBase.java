package com.quebic.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.quebic.common.async.response.AsyncResponseEntity;
import com.quebic.common.exception.ListenerTypeNotFound;
import com.quebic.common.sse.ListenerType;
import com.quebic.common.sse.SseEmitterApplicationEventListener;
import com.quebic.common.sse.SubmissionEvent;

import rx.Observable;
import rx.Single;

public class ControllerBase {
	
	@Autowired
	private SseEmitterApplicationEventListener applicationEventListener;
	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
	private ListenerType listenerType;
	
	public ControllerBase() {
	}
	
	public ControllerBase(ListenerType listenerType){
		this.listenerType = listenerType;
	}

	protected <T> ResponseEntity<T> makeResponse(T message) {
		return makeResponse(message, null, HttpStatus.OK);
	}

	protected <T> ResponseEntity<T> makeResponse(T message, HttpStatus status) {
		return makeResponse(message, null, status);
	}
	
	protected <T> ResponseEntity<T> makeResponse(T message, MultiValueMap<String, String> headers, HttpStatus status) {
		return new ResponseEntity<T>(message, headers, status);
	}

	/*
	 * Observable
	 */
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Observable<T> observable) {
		return makeAsyncResponse(observable, null, HttpStatus.OK);
	}

	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Observable<T> observable, HttpStatus status) {
		return makeAsyncResponse(observable, null, status);
	}
	
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Observable<T> observable, MultiValueMap<String, String> headers,HttpStatus status) {
		return AsyncResponseEntity.<T>status(status).headers(headers).observable(observable);
	}
	/*
	 * Observable
	 */
	
	/*
	 * Single
	 */
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Single<T> single) {
		return makeAsyncResponse(single, null, HttpStatus.OK);
	}
	
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Single<T> single, HttpStatus status) {
		return makeAsyncResponse(single, null, status);
	}

	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Single<T> single, MultiValueMap<String, String> headers,HttpStatus status) {
		return AsyncResponseEntity.<T>status(status).headers(headers).single(single);
	}
	/*
	 * Single
	 */
	
	/*
	 * Server-Sent-Event
	 */
	@RequestMapping("/listener/{id}")
	public SseEmitter  listen(@PathVariable("id") String id){
		
		if(this.listenerType == null)
			throw new ListenerTypeNotFound();
		
		final SseEmitter sseEmitter = new SseEmitter();
		applicationEventListener.addSseEmitters(this.listenerType , id, sseEmitter);
		return sseEmitter;
		
	}
	
	public void  publishMessage(String id, Object message){
		
		if(this.listenerType == null)
			throw new ListenerTypeNotFound();
		
		SubmissionEvent submissionEvent = new SubmissionEvent(this.listenerType, id, message);
		eventPublisher.publishEvent(submissionEvent);
		
	}
	
	/*
	 * Server-Sent-Event
	 */

}
