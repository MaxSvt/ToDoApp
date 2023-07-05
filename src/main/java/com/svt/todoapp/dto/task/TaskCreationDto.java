package com.svt.todoapp.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCreationDto {
    private String title;
    private String description;
}
