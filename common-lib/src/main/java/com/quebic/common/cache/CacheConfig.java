package com.quebic.common.cache;

import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

	private static final String SUFFIX = "secondary";
	private static final String CONCAT = ":";
	
    public static CacheManager createCacheManager(String... caches) {
		List<ConcurrentMapCache> concurrentMapCaches = new ArrayList<>();
		for(String cacheName : caches){
			concurrentMapCaches.add(new ConcurrentMapCache(cacheName));
			concurrentMapCaches.add(new ConcurrentMapCache(cacheName + CONCAT + SUFFIX));
		}
		
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(concurrentMapCaches);
        return cacheManager;
    }
	
	@Bean
	public CacheResolver primaryCacheResolver(CacheManager cacheManager){
		return new ClassNameBasedCacheResolver(cacheManager);
	}
	
	@Bean
	public CacheResolver secondaryCacheResolver(CacheManager cacheManager){
		return new ClassNameBasedCacheResolver(cacheManager, SUFFIX, CONCAT);
	}
	
	@Bean
	public KeyGenerator customKeyGenerator(){
		return new MethodSignatureBasedKeyGenerator();
	}
	
}
