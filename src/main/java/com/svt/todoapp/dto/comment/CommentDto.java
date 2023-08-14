package com.svt.todoapp.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String description;
    private String creationDate;
    private boolean isUpdated;
}
