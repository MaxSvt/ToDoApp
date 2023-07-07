package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.UpdateTaskStatusDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.Project;
import com.svt.todoapp.models.Task;
import com.svt.todoapp.models.enums.TaskStatus;
import com.svt.todoapp.repositories.ProjectRepository;
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
    private final Mapper mapper;

    @Autowired
    private final TaskRepository taskRepository;

    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public List<TaskDto> getAll(Long projectId) {
        return taskRepository.findByProjectId(projectId).stream().map(mapper::toTaskDto).collect(Collectors.toList());
//        return taskRepository.findAll().stream().map(mapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto getById(Long id) {
        return mapper.toTaskDto(taskRepository.findById(id).orElse(null));
    }

    @Override
    public void create(Long projectId, TaskCreationDto taskDto) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Task task = mapper.toTaskEntity(taskDto);
        assert project != null;
        project.addTask(task);
        taskRepository.save(task);
    }

    @Override
    public TaskDto update(Long id, TaskCreationDto taskDto) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        if(taskDto.getTitle().isEmpty() || taskDto.getDescription().isEmpty()){
            new NullPointerException().getMessage();
        }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setChangedDate(new Date());
        taskRepository.save(task);
        return mapper.toTaskDto(task);
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
        return mapper.toTaskDto(task);
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
