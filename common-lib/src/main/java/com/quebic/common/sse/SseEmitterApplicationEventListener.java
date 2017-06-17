package com.quebic.common.sse;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class SseEmitterApplicationEventListener {

	@EventListener
    public void submissionEventHandler(SubmissionEvent event) throws IOException {
        
		String key = event.getKey();
        Object message = event.getMessage();
        
        SseEmitter sseEmitter = sseEmitters.get(key);
 
        if ( sseEmitter == null ) {
            return;
        }
        
        sseEmitter.send(message, MediaType.APPLICATION_JSON);
    }
 
    public void addSseEmitters(ListenerType listenerType ,String id, SseEmitter sseEmitter) {
        sseEmitters.put(listenerType.prepareKey(id), sseEmitter);
    }
 
    private static Map<String, SseEmitter> sseEmitters = new Hashtable<>();
	
}
