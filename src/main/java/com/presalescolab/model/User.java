package com.presalescolab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	

	private static final long serialVersionUID = 7588521043698608588L;
	private int id;
	private String firstName;
	private List<String> groups = new ArrayList<>();
	
	
	public User()
	{
		
	}
	
	public User(int id,String firstName)
	{
		this.id=id;
		this.firstName=firstName;
		this.groups.add("USER");
	}
	
	
	public User(int id, String firstName, List<String> groups) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.groups = groups;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}

}
