package com.svt.todoapp.mapping;

import com.svt.todoapp.dto.TaskCreationDto;
import com.svt.todoapp.dto.TaskDto;
import com.svt.todoapp.models.Task;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class TaskMapper {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM yyyy");

    public TaskDto toDto(Task task){
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().getTitle(),
                DATE_FORMAT.format(task.getCreatedDate()),
                DATE_FORMAT.format(task.getChangedDate())
        );
    }

    public Task toEntity(TaskCreationDto dto){
        return new Task(dto.getTitle(), dto.getDescription());
    }
}
