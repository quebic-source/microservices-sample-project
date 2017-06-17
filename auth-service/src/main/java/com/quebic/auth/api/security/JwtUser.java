package com.quebic.auth.api.security;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quebic.auth.api.model.User;

public class JwtUser implements UserDetails {

	private static final long serialVersionUID = -1728234246191170692L;
	
	private User user;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public JwtUser(
    		User user
			, Collection<? extends GrantedAuthority> authorities
			, boolean enabled) {
		
    	this.user = user;
		this.authorities = authorities;
		this.enabled = enabled;
		
	}

    public User getDbUser() {
		return user;
	}
    
    public Date getLastPasswordResetDate() {
        return user.getLastPasswordResetDate();
    }

	@Override
    public String getUsername() {
        return user.getEmail();
    }
    
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
}
