package com.svt.todoapp.dto.task;

import com.svt.todoapp.dto.comment.CommentDto;
import com.svt.todoapp.dto.project.ProjectSlimDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String createdDate;
    private String changedDate;
    private ProjectSlimDto project;
    private List<CommentDto> comments;
}
