package com.presalescolab.model;

public class Project {
	
	private String projectName;
	private Integer projectId;
	
	public Project(){}

	public Project(String projectName,Integer projectId)
	{
		this.projectName=projectName;
		this.projectId=projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getprojectId() {
		return projectId;
	}

	public void setProjectID(Integer projectId) {
		this.projectId = projectId;
	}

}
