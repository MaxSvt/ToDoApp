package com.svt.todoapp.services;

import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAll(Long projectId);

    TaskDto getById(Long id);

    void create(Long projectId, TaskCreationDto task);

    TaskDto update(Long id, TaskDto taskDto);

    void delete(Long id);

}
