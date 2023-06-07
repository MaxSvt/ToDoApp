package com.svt.todoapp.dto;

import com.svt.todoapp.models.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String createdDate;
    private String changedDate;

    public TaskDto(){

    }
}
