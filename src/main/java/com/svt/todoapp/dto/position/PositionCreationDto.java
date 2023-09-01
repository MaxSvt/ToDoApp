package com.svt.todoapp.dto.position;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PositionCreationDto {

    @NotBlank
    private String name;
}
