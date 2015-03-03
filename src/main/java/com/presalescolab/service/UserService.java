package com.presalescolab.service;

import com.presalescolab.model.User;



public interface UserService {

	User getUserByCredentials(String name, String password);
	


}
