package com.quebic.common.dao.impl;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.quebic.common.dao.SequenceDao;
import com.quebic.common.exception.DataAccessException;
import com.quebic.common.model.Sequence;

@Repository
public class SequenceDaoImpl extends GenericDaoImpl<Sequence>implements SequenceDao {

	public SequenceDaoImpl() {
		super(Sequence.class);
	}

	@Override
	public int getNextSequenceId(String key) throws DataAccessException {
		
		//get sequence id
		Query query = new Query(Criteria.where("id").is(key));

		//increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		//return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		//this is the magic happened.
		Sequence seqId = mongoOperations.findAndModify(query, update, options, Sequence.class);

		// if no id, throws SequenceException
		// optional, just a way to tell user when the sequence id is failed to
		// generate.
		if (seqId == null) {
			seqId = new Sequence();
			seqId.setId(key);
			seqId.setSeq(1);
			mongoOperations.insert(seqId);
			return 1;
		}else{
			return seqId.getSeq();
		}

	}

}
