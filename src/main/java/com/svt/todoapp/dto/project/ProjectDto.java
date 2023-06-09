package com.svt.todoapp.dto.project;

import com.svt.todoapp.dto.task.TaskSlimDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private List<TaskSlimDto> tasks;
}
