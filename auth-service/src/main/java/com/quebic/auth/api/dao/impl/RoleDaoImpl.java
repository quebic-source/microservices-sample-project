package com.quebic.auth.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.quebic.auth.api.dao.RoleDao;
import com.quebic.auth.api.model.Role;
import com.quebic.common.dao.impl.GenericDaoImpl;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}
	
}
