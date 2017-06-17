package com.quebic.common.cache;

import java.lang.reflect.Method;
import org.springframework.cache.interceptor.KeyGenerator;

public class MethodSignatureBasedKeyGenerator implements KeyGenerator {
	
	@Override
	public Object generate(Object target, Method method, Object... params) {
		StringBuilder key = new StringBuilder();
		key.append(method.getName());
		if (params.length > 0) {
			key.append(':');
			for (Object argument : params) {
				key.append(argument);
				key.append(':');
			}
		}
		System.out.println("key : " + key.toString());
		return key.toString();
	}
}
