package com.quebic.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

	public static UserDetails getLoggedUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
        	return (UserDetails) principal;

        return null;
    }
	
	public static AuthUser getAuthUserDetails() {
        UserDetails userDetails = getLoggedUserDetails();

        if (userDetails != null && (userDetails instanceof AuthUser))
        	return (AuthUser) userDetails;

        return null;
    }
	
}
