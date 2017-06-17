package com.quebic.common.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUser implements UserDetails {

	private static final long serialVersionUID = 2061709915172252885L;
	private String userId;
	private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    
	public AuthUser(String userId, String username, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		this.userId = userId;
		this.username = username;
		this.authorities = authorities;
		this.enabled = enabled;
	}
	
	public String getUserId() {
		return userId;
	}

	@Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

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

	@Override
	public String toString() {
		return "AuthUser [userId=" + userId + ", username=" + username + ", authorities=" + authorities + ", enabled="
				+ enabled + "]";
	}
    
}
