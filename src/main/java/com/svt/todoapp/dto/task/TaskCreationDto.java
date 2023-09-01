package com.svt.todoapp.dto.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class TaskCreationDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String performer;
}
