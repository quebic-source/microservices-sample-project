package com.quebic.auth.api.service.impl;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quebic.auth.api.dao.PermissionDao;
import com.quebic.auth.api.service.PermissionService;
import com.quebic.common.dao.SequenceDao;
import com.quebic.common.exception.DataAccessException;
import com.quebic.common.model.Permission;
import com.quebic.common.service.impl.GenericServiceImpl;

import rx.Single;;

@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission> implements PermissionService{

	@Autowired
	protected PermissionDao permissionDao;
	
	@Autowired
	protected SequenceDao sequenceDao;
	
	@PostConstruct
	void init() {
        init(Permission.class, permissionDao);
    }
	
	@Override
	public Single<Permission> add(Permission permission){
		try{
			permissionDao.add(permission);
			return Single.just(permission);
		}catch (DataAccessException e) {
			return Single.error(e);
        }
	}
}
