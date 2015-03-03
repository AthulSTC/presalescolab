package com.presalescolab.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.presalescolab.model.User;
import com.presalescolab.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Override
	public User getUserByCredentials(String name, String password) {

		User user = null;
		
		switch(name)
		{
		case "presales" :
			
			if("presales".equals(password))
			{
				user = new User(1,"Presales");
			}
			
			break;
		
		case "sme" :
			
			if("sme".equals(password))
			{
				user = new User(1,"SME");
			}
			
			break;
			
		case "manager" :
			
			if("manager".equals(password))
			{
				user = new User(1,"Manager");
			}

			break;
			
		case "other" :
			
			if("other".equals(password))
			{
				user = new User(1,"Other");
			}
			
			break;
			
		default :
		
			break;
		}
		
		return user;
	}


}
