package com.svt.todoapp.controllers;

import com.svt.todoapp.models.Task;
import com.svt.todoapp.services.impl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping
    Iterable<Task> getAll(){
        return taskService.getAll();
    }

    @GetMapping(value = "/{id}")
    Task getById(@PathVariable("id") Long id){
        return taskService.getById(id);
    }

    @PostMapping
    ResponseEntity<Task> create(@RequestBody Task resource){
        taskService.create(resource);
        return ResponseEntity.ok().build();
    }
}
