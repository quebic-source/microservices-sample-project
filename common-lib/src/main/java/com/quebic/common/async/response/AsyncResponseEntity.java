package com.quebic.common.async.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import rx.Observable;
import rx.Single;

public class AsyncResponseEntity<T>{
	
	private Observable<T> observable;
	private Single<T> single;
	private MultiValueMap<String, String> headers;
	private HttpStatus status;
	
	public AsyncResponseEntity(
			Observable<T> observable
			, MultiValueMap<String
			, String> headers,
			HttpStatus status
			) {
		this.observable = observable;
		this.headers = headers;
		this.status = status;
	}
	
	public AsyncResponseEntity(
			Single<T> single
			, MultiValueMap<String
			, String> headers,
			HttpStatus status
			) {
		this.single = single;
		this.headers = headers;
		this.status = status;
	}
	
	public AsyncResponseEntity(HttpStatus status) {
		this.status = status;
	}

	public Observable<T> getObservable() {
		return observable;
	}

	public Single<T> getSingle() {
		return single;
	}

	public MultiValueMap<String, String> getHeaders() {
		return headers;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public static <T> AsyncResponseEntity<T> status(HttpStatus status){
		return new AsyncResponseEntity<T>(status);
	}
	
	public AsyncResponseEntity<T> headers(MultiValueMap<String, String> headers){
		this.headers = headers;
		return this;
	}
	
	public AsyncResponseEntity<T> observable(Observable<T> observable){
		this.observable = observable;
		this.single = null;
		return this;
	}
	
	public AsyncResponseEntity<T> single(Single<T> single){
		this.single = single;
		this.observable = null;
		return this;
	}
	
	public static <T> AsyncResponseEntity<T> ok(){
		return AsyncResponseEntity.status(HttpStatus.OK);
	}
	
	public static <T> AsyncResponseEntity<T> created(){
		return AsyncResponseEntity.status(HttpStatus.CREATED);
	}
	
	public static <T> AsyncResponseEntity<T> accepted(){
		return AsyncResponseEntity.status(HttpStatus.ACCEPTED);
	}
	
	public static <T> AsyncResponseEntity<T> noContent(){
		return AsyncResponseEntity.status(HttpStatus.NO_CONTENT);
	}
	
	public static <T> AsyncResponseEntity<T> badRequest(){
		return AsyncResponseEntity.status(HttpStatus.BAD_REQUEST);
	}
	
	public static <T> AsyncResponseEntity<T> notFound(){
		return AsyncResponseEntity.status(HttpStatus.NOT_FOUND);
	}
	
	public static <T> AsyncResponseEntity<T> unprocessableEntity(){
		return AsyncResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
