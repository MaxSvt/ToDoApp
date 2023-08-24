package com.svt.todoapp.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectSlimDto {
    private Long id;
    private String code;
    private String title;
    private String status;
}
