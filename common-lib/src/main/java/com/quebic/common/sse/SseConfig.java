package com.quebic.common.sse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SseConfig {

	@Bean
	public SseEmitterApplicationEventListener sseEventListener() {
        return new SseEmitterApplicationEventListener();
    }
	
}
