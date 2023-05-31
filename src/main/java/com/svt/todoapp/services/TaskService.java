package com.svt.todoapp.services;

import com.svt.todoapp.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAll();

    Task getById(Long id);

    Task create(Task task);

}
