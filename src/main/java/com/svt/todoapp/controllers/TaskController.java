package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.UpdateTaskStatusDto;
import com.svt.todoapp.exceptions.ResourceNotFoundException;
import com.svt.todoapp.models.Task;
import com.svt.todoapp.repositories.ProjectRepository;
import com.svt.todoapp.repositories.TaskRepository;
import com.svt.todoapp.services.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @GetMapping(value = "/projects/{projectId}/tasks")
    public Iterable<TaskDto> getAll(@PathVariable(value = "projectId") Long projectId){
        if (!projectRepository.existsById(projectId)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projectId);
        }
        return taskService.getAll(projectId);
    }

    @GetMapping(value = "/tasks/{id}")
    public TaskDto getById(@PathVariable("id") Long id){
        return taskService.getById(id);
    }

    @PostMapping(value = "/projects/{projectId}/tasks")
    public ResponseEntity<TaskDto> create(@PathVariable(value = "projectId") Long projectId, @RequestBody TaskCreationDto taskDto){
        taskService.create(projectId, taskDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/tasks/{id}")
    public ResponseEntity<TaskDto> updateStatus(@PathVariable(value = "id") Long id, @RequestBody UpdateTaskStatusDto statusDto){
        TaskDto postResponse = taskService.updateStatus(id, statusDto);
        return ResponseEntity.ok().body(postResponse);
    }

    @PutMapping(value = "/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable(value = "id") Long id, @RequestBody TaskCreationDto taskDto){
        TaskDto postResponse = taskService.update(id, taskDto);
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public String deleteTask(@PathVariable(value = "id") Long id){
        taskService.delete(id);
        return "Task has been deleted successfully";
    }

    @DeleteMapping(value = "/projects/{projectId}/tasks")
    public ResponseEntity<List<Task>> deleteAllTasksOfProject(@PathVariable(value = "projectId") Long projectId){
        if(!projectRepository.existsById(projectId)){
            throw new ResourceNotFoundException("Not found Project with id =" + projectId);
        }
        taskRepository.deleteByProjectId(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}