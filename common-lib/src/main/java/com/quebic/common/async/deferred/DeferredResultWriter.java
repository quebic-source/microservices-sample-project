package com.quebic.common.async.deferred;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Subscriber;

public class DeferredResultWriter<T> extends Subscriber<T> implements Runnable{

	private final DeferredResult<ResponseEntity<T>> deferredResult;
	private final MultiValueMap<String, String> headers;
	private final HttpStatus status;

    public DeferredResultWriter(
    		Observable<T> observable
    		, DeferredResult<ResponseEntity<T>> deferredResult
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        
    	this.deferredResult = deferredResult;
        this.headers = headers;
        this.status = status;
        
        this.deferredResult.onTimeout(this);
        this.deferredResult.onCompletion(this);
        observable.subscribe(this);
    }

    @Override
    public void onNext(T value) {
        deferredResult.setResult(new ResponseEntity<>(value, headers, status));
    }

    @Override
    public void onError(Throwable e) {
        deferredResult.setErrorResult(e);
    }

    @Override
    public void run() {
        this.unsubscribe();
    }

	@Override
	public void onCompleted() {
	}

}
