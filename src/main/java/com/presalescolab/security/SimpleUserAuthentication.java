package com.presalescolab.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.presalescolab.model.Credentials;
import com.presalescolab.model.User;

public class SimpleUserAuthentication implements Authentication {

    private User user;
    private Credentials creds;
    private boolean authenticated;
	private static final long serialVersionUID = 3321207634948803791L;

	public SimpleUserAuthentication(User user, Credentials creds) {
		super();
		this.user = user;
		this.creds = creds;
	}

	public SimpleUserAuthentication(User user) {
		super();
		this.user = user;
	}
	
	public SimpleUserAuthentication(Credentials creds) {
		super();
		this.creds = creds;
	}

	@Override
	public String getName() {
		return creds.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getGroups() == null)
            return new ArrayList<>();

        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String roles : user.getGroups()) {
            authorities.add(new SimpleGrantedAuthority(roles));
        }
        return authorities;
	}

	@Override
	public Object getCredentials() {
		return creds;
	}

	@Override
	public Object getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated=authenticated;
		
	}

}
