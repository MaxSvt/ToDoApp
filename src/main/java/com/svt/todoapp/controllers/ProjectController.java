package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.services.impl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    @Autowired
    private final ProjectServiceImpl projectService;

    @GetMapping()
    public Iterable<ProjectDto> getAll(){
        return projectService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ProjectDto getById(@PathVariable Long id){
        return projectService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ProjectDto>  create(@RequestBody ProjectCreationDto creationDto){
        projectService.create(creationDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto){
        ProjectDto postResponse = projectService.update(id, projectDto);
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteProject(@PathVariable Long id){
        projectService.delete(id);
        return "Project has been deleted successfully";
    }

}
