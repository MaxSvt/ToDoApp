package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.participant.ParticipantCreationDto;
import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.exceptions.AppError;
import com.svt.todoapp.services.impl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/api/v1/projects")
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
    public ResponseEntity<?> create(@RequestBody ProjectCreationDto creationDto, Principal principal){
        if(creationDto.getTitle().isEmpty() || creationDto.getDescription().isEmpty() || creationDto.getCode().isEmpty()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Fill title or description or code"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(projectService.create(creationDto, principal.getName()), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}/participants")
    public ResponseEntity<?> addEmployee(@PathVariable(value = "id") Long id, @RequestBody ParticipantCreationDto dto){
        return new ResponseEntity<>(projectService.addEmployee(id, dto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id") Long id, @RequestBody ProjectCreationDto projectDto){
        ProjectDto postResponse = projectService.update(id, projectDto);
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteProject(@PathVariable Long id){
        projectService.delete(id);
        return "Project has been deleted successfully";
    }

}
