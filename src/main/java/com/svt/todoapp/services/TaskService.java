package com.svt.todoapp.services;

import com.svt.todoapp.dto.TaskCreationDto;
import com.svt.todoapp.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAll();

    TaskDto getById(Long id);

    void create(TaskCreationDto task);

    TaskDto update(Long id, TaskDto taskDto);

    void delete(Long id);

}
