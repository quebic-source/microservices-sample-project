package com.quebic.auth.api.dao;

import java.util.List;

import com.quebic.auth.api.model.User;
import com.quebic.common.dao.GenericDao;
import com.quebic.common.exception.DataAccessException;
import com.quebic.common.model.Permission;

public interface UserDao extends GenericDao<User>{
	
	User findByEmail(String email) throws DataAccessException;

	List<Permission> findUserPermissions()throws DataAccessException;
	
	User findUserByToken(String token)throws DataAccessException;

	User findByUsernameOrEmail(String username, String email) throws DataAccessException;
	
}
