package com.quebic.auth.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.quebic.auth.api.dao.PermissionDao;
import com.quebic.common.dao.impl.GenericDaoImpl;
import com.quebic.common.model.Permission;

@Repository
public class PermissionDaoImpl extends GenericDaoImpl<Permission> implements PermissionDao {

	public PermissionDaoImpl() {
		super(Permission.class);
	}
}
