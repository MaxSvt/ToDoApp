package com.svt.todoapp.dto.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentCreationDto {

    @NotBlank
    private String description;
}
