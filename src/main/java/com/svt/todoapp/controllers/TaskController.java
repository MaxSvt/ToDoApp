package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.UpdateTaskStatusDto;
import com.svt.todoapp.services.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping
    public Iterable<TaskDto> getAll(){
        return taskService.getAll();
    }

    @GetMapping(value = "/{id}")
    public TaskDto getById(@PathVariable("id") Long id){
        return taskService.getById(id);
    }

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskCreationDto taskDto){
        taskService.create(taskDto);
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