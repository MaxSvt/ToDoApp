package com.svt.todoapp.dto.project;

import com.svt.todoapp.dto.participant.ParticipantDto;
import com.svt.todoapp.dto.task.TaskSlimDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String title;
    private String code;
    private String description;
    private String projectManager;
    private String status;
    private List<ParticipantDto> participants;
    private List<TaskSlimDto> tasks;
}
