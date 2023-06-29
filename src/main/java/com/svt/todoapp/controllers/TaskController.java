package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.UpdateTaskStatusDto;
import com.svt.todoapp.exceptions.ResourceNotFoundException;
import com.svt.todoapp.repositories.ProjectRepository;
import com.svt.todoapp.services.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;
    private final ProjectRepository projectRepository;

    @GetMapping(value = "/projects/{projectId}/tasks")
    public Iterable<TaskDto> getAll(@PathVariable(value = "projectId") Long projectId){
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projectId);
        }
        return taskService.getAll(projectId);
    }

    @GetMapping(value = "/projects/{projectId}/tasks/{id}")
    public TaskDto getById(@PathVariable("projectId") Long projectId, @PathVariable("id") Long id){
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projectId);
        }
        return taskService.getById(projectId, id);
    }

    @PostMapping(value = "/projects/{projectId}/tasks")
    public ResponseEntity<TaskDto> create(@PathVariable(value = "projectId") Long projectId, @RequestBody TaskCreationDto taskDto){
        taskService.create(projectId, taskDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto){
        TaskDto postResponse = taskService.update(id, taskDto);
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.delete(id);
        return "Task has been deleted successfully";
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<TaskDto> updateStatus(@PathVariable Long id, @RequestBody UpdateTaskStatusDto statusDto){
        TaskDto postResponse = taskService.updateStatus(id, statusDto);
        return ResponseEntity.ok().body(postResponse);
    }

}