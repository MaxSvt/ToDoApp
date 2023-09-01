package com.svt.todoapp.dto.project;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProjectCreationDto {

    @NotBlank
    private String title;

    @NotBlank
    private String code;

    @NotBlank
    private String description;
}
