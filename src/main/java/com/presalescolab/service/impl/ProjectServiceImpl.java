package com.presalescolab.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Service;
import com.presalescolab.model.Project;
import com.presalescolab.service.ProjectService;


@Service
public class ProjectServiceImpl   implements ProjectService,ApplicationListener<BrokerAvailabilityEvent>{

	private static Log logger = LogFactory.getLog(ProjectServiceImpl.class);
	private Map<Integer,Project> projectRepo = new HashMap<>();
	private AtomicBoolean brokerAvailable = new AtomicBoolean();
	private final MessageSendingOperations<String> messagingTemplate;
	
	
	
	@Autowired
	public ProjectServiceImpl(MessageSendingOperations<String> messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
	
	@PostConstruct
	private void loadProjectRepo()
	{
		Project project1 = new Project("Test Project 1", new Integer(1));
		Project project2 = new Project("Test Project 2", new Integer(2));
		 this.projectRepo.put(project1.getprojectId(), project1);
		 this.projectRepo.put(project2.getprojectId(), project2);
	}
	
	
	@Override
	public synchronized List<Project> getProjectList()
	{
				
		Set<Integer> projectIDs= projectRepo.keySet();
		List<Project> projectList= new ArrayList<>();		
		for(Integer projectID : projectIDs)
		{
			projectList.add(projectRepo.get(projectID));
		}		
		return projectList;		
	}
	
	@Override
	public synchronized Project getProject(Integer projectID)
	{
		return this.projectRepo.get(projectID);
	}
		
	@Override
	public synchronized void updateProject(Project projectNew)
	{
		
		Project proiectOld= this.projectRepo.get(projectNew.getprojectId());		
		proiectOld.setProjectName(projectNew.getProjectName());
		
		if (this.brokerAvailable.get()) {
			logger.info("Sending updated project " + projectNew);
			this.messagingTemplate.convertAndSend("/topic/project/" + projectNew.getprojectId(), projectNew);
		}
		
		
	}


	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent event) {
		this.brokerAvailable.set(event.isBrokerAvailable());	
	}



	
	
}
