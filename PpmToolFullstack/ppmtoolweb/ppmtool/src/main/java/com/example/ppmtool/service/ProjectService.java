package com.example.ppmtool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.exception.ProjectIdException;
import com.example.ppmtool.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdate(Project project) {
		try {
			
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch (Exception e) {
			throw new ProjectIdException("Project ID : '"+project.getProjectIdentifier()+"' already exists");
		}
	}
	
	
	public Project findProjectByIdentifier(String projectIdentifier) {
		Project project = (Project) projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project ID : "+projectIdentifier.toUpperCase()+ " does not exist");
		}
		return project;
	}
	
	
	
	public Iterable<Project> findAllProject(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectIdentifier) {
		Project project = (Project) projectRepository.findByProjectIdentifier(projectIdentifier);
		if(project == null) {
			throw new ProjectIdException("Project ID : "+projectIdentifier.toUpperCase()+ " does not exist");
		}
		projectRepository.delete(project);
		
	}
	

	
	
	
	
	
	
	
	
	public Optional<Project> delete(Long id) {
		Optional<Project> project = projectRepository.findById(id);
		projectRepository.deleteById(id);
		return project;
	}
	
	public Optional<Project> saveOrUpdate(Project project,Long id) {
		projectRepository.save(project);
		return projectRepository.findById(id);
	}
	
	public Optional<Project> getProject(Long id) {
		return projectRepository.findById(id);
	}

	
}
