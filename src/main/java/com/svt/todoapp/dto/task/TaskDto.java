package com.svt.todoapp.dto.task;

import com.svt.todoapp.dto.project.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}
