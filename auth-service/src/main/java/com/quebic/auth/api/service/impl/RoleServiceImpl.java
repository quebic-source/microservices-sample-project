package com.quebic.auth.api.service.impl;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quebic.auth.api.dao.RoleDao;
import com.quebic.auth.api.model.Role;
import com.quebic.auth.api.service.RoleService;
import com.quebic.common.dao.SequenceDao;
import com.quebic.common.exception.DataAccessException;
import com.quebic.common.service.impl.GenericServiceImpl;

import rx.Single;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService{

	@Autowired
	protected RoleDao roleDao;
	
	@Autowired
	protected SequenceDao sequenceDao;
	
	@PostConstruct
	void init() {
        init(Role.class, roleDao);
    }
	
	@Override
	public Single<Role> add(Role role){
		try{
			roleDao.add(role);
			return Single.just(role);
		}catch (DataAccessException e) {
			return Single.error(e);
        }
	}
}
