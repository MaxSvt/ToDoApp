package com.svt.todoapp.dto.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectCreationDto {
    private String title;
    private String code;
    private String description;
}
