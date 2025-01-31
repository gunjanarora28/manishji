package com.example.ppmtool.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppmtool.domain.Project;
import com.example.ppmtool.service.MapValidationErrorService;
import com.example.ppmtool.service.ProjectService;

@RestController
@RequestMapping("/web/projects")
@CrossOrigin
public class ProjectController {

	@Autowired
	private ProjectService projectService; 
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@PostMapping("")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project,BindingResult result){
		
		ResponseEntity<?> errMap = mapValidationErrorService.mapValidationError(result);
		if(errMap != null) {
			return errMap;
		}
		projectService.saveOrUpdate(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	} 
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectByIdentifier(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAllProjects(){
		Iterable<Project> projects = projectService.findAllProject();
		return new ResponseEntity<Iterable>(projects,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{projectId}")
	public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectId){
		projectService.deleteProjectByIdentifier(projectId);
		return new ResponseEntity<String>("Project with "+projectId+" deleted Successfully",HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@GetMapping("/{id}")
	public Optional<Project> getProjectById(@PathVariable Long id){
		return projectService.getProject(id);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Project> deleteProject(@PathVariable Long id){
		projectService.delete(id);
		return new ResponseEntity<Project>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project){
		projectService.saveOrUpdate(project, id);
		return new ResponseEntity<Project>(HttpStatus.OK);
	}*/
}
