package com.quebic.auth.api.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quebic.auth.api.model.User;
import com.quebic.auth.api.security.JwtUser;
import com.quebic.auth.api.service.UserService;
import com.quebic.common.model.Permission;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Loading user - {}", username);

		UserDetails userDetails;

		User user;

		try {
			
			if (logger.isDebugEnabled())
				logger.debug("userService-{}", userService);
			
			//username may be email
			user = userService.findByUsernameOrEmail(username, username).toBlocking().value();
			
			if (logger.isDebugEnabled())
				logger.debug("user - {}", user);

			if (user == null) {
				throw new UsernameNotFoundException(username);
			}

			userDetails = new JwtUser(
					user
					, getAuthorities(user)
					, (user.getStatus() == 1)?true:false);
			
			if (userDetails.getAuthorities().isEmpty()) {
				logger.debug("User - {}, Permission count - 0", username);
				throw new UsernameNotFoundException(username);
			}

			return userDetails;

		} catch (Exception e) {
			if (logger.isErrorEnabled())
				logger.error("Service call error", e);
			throw new UsernameNotFoundException(username);
		}

	}

	public Collection<GrantedAuthority> getAuthorities(User user) {

		List<GrantedAuthority> authList = new ArrayList<>();
		for(Permission permission : user.getPermissions()){
			authList.add(new SimpleGrantedAuthority("ROLE_" + permission.getCode()));
		}
		return authList;

	}

}
