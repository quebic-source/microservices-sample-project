package com.quebic.common.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quebic.common.dao.impl.SequenceDaoImpl;

@Configuration
public class DaoConfig {

	@Bean
	public SequenceDao sequenceDao() {
        return new SequenceDaoImpl();
    }
	
}
