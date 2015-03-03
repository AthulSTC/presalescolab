package com.presalescolab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.presalescolab.model.Credentials;
import com.presalescolab.model.User;
import com.presalescolab.security.SimpleUserAuthentication;


@Controller
@RequestMapping("api/user")
public class UserController {
	
	
	@Autowired
	private WebSecurityConfigurerAdapter configurerAdapter;
	
	@ResponseBody
	@RequestMapping("login")
	public User login(@RequestBody Credentials credentials,HttpServletRequest request,HttpServletResponse response)
	{

		SimpleUserAuthentication authentication = new SimpleUserAuthentication(credentials);
		try {
			authentication = (SimpleUserAuthentication) configurerAdapter.authenticationManagerBean().authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			e.printStackTrace();
		}
		if (authentication.isAuthenticated() == false)
		{
			return null;
		}
			
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		User user = (User) authentication.getPrincipal();
		return user;
	}

}
