package com.presalescolab.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.presalescolab.model.Credentials;
import com.presalescolab.model.User;
import com.presalescolab.security.SimpleUserAuthentication;
import com.presalescolab.service.UserService;

@Service("internalAuthProvider")
public class PresalesInternalAuthenticationProvider implements AuthenticationProvider{

	
  @Autowired
  @Qualifier("userService")
  private UserService userService;
	
	

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String name=authentication.getName();		
		String password=((Credentials) authentication.getCredentials()).getPassword();
		
		User user = userService.getUserByCredentials(name, password);
		
		if(user != null)
		{
            SimpleUserAuthentication auth = new SimpleUserAuthentication(user, (Credentials) authentication.getCredentials() );
            auth.setAuthenticated(true);
            return auth;
		}

		throw new AuthenticationCredentialsNotFoundException("Invalid userid and password");
	}

	public boolean supports(Class<?> authentication) {		
		return authentication.equals(SimpleUserAuthentication.class);
	}

}
