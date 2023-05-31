package com.svt.todoapp.services.impl;

import com.svt.todoapp.models.Task;
import com.svt.todoapp.repositories.TaskRepository;
import com.svt.todoapp.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM yyyy");

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task getById(Long id) throws ConfigDataResourceNotFoundException {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()){
            return task.get();
        }
       throw new RuntimeException();
    }

    @Override
    public Task create(Task task) {
        task.setCreatedDate(new Date());
        task.setChangedDate(new Date());
        task.setComplete(false);
        return taskRepository.save(task);
    }
}
