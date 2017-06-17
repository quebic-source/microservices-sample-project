package com.quebic.common.cache;

import java.util.Collection;
import java.util.Collections;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;

public class ClassNameBasedCacheResolver extends AbstractCacheResolver{

	public String suffix = "";
	public String concat;
	
	public ClassNameBasedCacheResolver(CacheManager cacheManager){
		super(cacheManager);
	}
	
	public ClassNameBasedCacheResolver(CacheManager cacheManager, String suffix, String concat){
		super(cacheManager);
		this.suffix = suffix;
		this.concat = concat;
	}
	
	@Override
	protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
		String cls = context.getTarget().getClass().getSimpleName() 
				+ (suffix.equals("")?suffix:concat+suffix);
		return Collections.singletonList(cls);
	}
	
}
