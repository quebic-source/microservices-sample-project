package com.quebic.common.async.deferred;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import java.util.List;

public class ObservableDeferredResult<T> extends DeferredResult<ResponseEntity<List<T>>> {

    private static final Object EMPTY_RESULT = new Object();

    public ObservableDeferredResult(
    		Observable<T> observable
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        this(null, EMPTY_RESULT, observable, headers, status);
    }

    public ObservableDeferredResult(
    		Long timeout
    		, Observable<T> observable
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        this(timeout, EMPTY_RESULT, observable, headers, status);
    }

    public ObservableDeferredResult(
    		Long timeout
    		, Object timeoutResult
    		, Observable<T> observable
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        super(timeout, timeoutResult);
        new DeferredResultWriter<List<T>>(observable.toList(), this, headers, status);
    }
}
