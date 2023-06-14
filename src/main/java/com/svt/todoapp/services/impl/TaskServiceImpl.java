package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.TaskCreationDto;
import com.svt.todoapp.dto.TaskDto;
import com.svt.todoapp.dto.UpdateTaskStatusDto;
import com.svt.todoapp.mapping.TaskMapper;
import com.svt.todoapp.models.Task;
import com.svt.todoapp.models.enums.TaskStatus;
import com.svt.todoapp.repositories.TaskRepository;
import com.svt.todoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskMapper taskMapper;

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll().stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto getById(Long id) {
        return taskMapper.toDto(Objects.requireNonNull(taskRepository.findById(id).orElse(null)));
    }

    @Override
    public void create(TaskCreationDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
    }

    @Override
    public TaskDto update(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setChangedDate(new Date());
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto updateStatus(Long id, UpdateTaskStatusDto statusDto){
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        if(Objects.requireNonNull(checkStatus(statusDto.getStatus())).ordinal() - task.getStatus().ordinal() == 1){
            switch (task.getStatus()){
                case NEW -> task.setStatus(TaskStatus.IN_WORK);
                case IN_WORK -> task.setStatus(TaskStatus.COMPLETED);
                case COMPLETED -> task.setStatus(TaskStatus.CLOSED);
            }
        }
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    private static TaskStatus checkStatus(String status){
        for(TaskStatus taskStatus : TaskStatus.values()){
            if(taskStatus.getTitle().equals(status)){
                return taskStatus;
            }
        }
        // Добавить исключение!!!
        return null;
    }

}
