package com.svt.todoapp.dto.project;


import com.svt.todoapp.dto.task.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private List<TaskDto> tasks;
}
