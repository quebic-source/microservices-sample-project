package com.quebic.common.async.deferred;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Single;

public class SingleDeferredResult <T> extends DeferredResult<ResponseEntity<T>> {

    private static final Object EMPTY_RESULT = new Object();

    public SingleDeferredResult(
    		Single<T> single
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        this(null, EMPTY_RESULT, single, headers, status);
    }

    public SingleDeferredResult(
    		long timeout
    		, Single<T> single
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        this(timeout, EMPTY_RESULT, single, headers, status);
    }

    public SingleDeferredResult(
    		Long timeout
    		, Object timeoutResult
    		, Single<T> single
    		, MultiValueMap<String, String> headers
    		, HttpStatus status) {
        super(timeout, timeoutResult);
        new DeferredResultWriter<T>(single.toObservable(), this, headers, status);
    }
}
