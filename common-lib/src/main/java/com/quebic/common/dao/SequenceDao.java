package com.quebic.common.dao;

import com.quebic.common.exception.DataAccessException;
import com.quebic.common.model.Sequence;

public interface SequenceDao  extends GenericDao<Sequence>{

	int getNextSequenceId(String key) throws DataAccessException;
}
