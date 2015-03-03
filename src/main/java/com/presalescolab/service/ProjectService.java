package com.presalescolab.service;


import java.util.List;


import com.presalescolab.model.Project;

public interface ProjectService {
	
	public List<Project> getProjectList();
	
	public Project getProject(Integer projectID);
		
	public void updateProject(Project projectNew);
	

}
