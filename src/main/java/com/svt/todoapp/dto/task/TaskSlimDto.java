package com.svt.todoapp.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskSlimDto {
    private Long id;
    private String title;
    private String status;
}
