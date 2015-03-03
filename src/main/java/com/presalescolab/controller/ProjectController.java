package com.presalescolab.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.presalescolab.model.Project;
import com.presalescolab.model.Trade;
import com.presalescolab.service.ProjectService;


@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	

	@SubscribeMapping(value="/project/list")
	public List<Project> getProjetList(Principal principal)
	{
		return projectService.getProjectList();
	}
	
	
	@MessageMapping("/update")
	public void updateProject(Project project)
	{
	   projectService.updateProject(project);
	}
	
		

}
