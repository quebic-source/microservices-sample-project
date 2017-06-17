package com.quebic.common.async.handlers;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.quebic.common.async.deferred.ObservableDeferredResult;
import com.quebic.common.async.deferred.SingleDeferredResult;
import com.quebic.common.async.response.AsyncResponseEntity;

import rx.Observable;
import rx.Single;

public class AsyncResponseEntityReturnHandler implements AsyncHandlerMethodReturnValueHandler{

	@Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return returnValue != null && supportsReturnType(returnType);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return AsyncResponseEntity.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }

        final AsyncResponseEntity<?> asyncResponseEntity = AsyncResponseEntity.class.cast(returnValue);
        
        Observable<?> observable = asyncResponseEntity.getObservable();
        Single<?> single = asyncResponseEntity.getSingle();
        MultiValueMap<String, String> headers =  asyncResponseEntity.getHeaders();
        HttpStatus status = asyncResponseEntity.getStatus();
        
        if(observable != null)
        	WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(new ObservableDeferredResult<>(observable, headers, status), mavContainer);
        else if(single != null)
        	WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(new SingleDeferredResult<>(single, headers, status), mavContainer);
        
    }
	
}
