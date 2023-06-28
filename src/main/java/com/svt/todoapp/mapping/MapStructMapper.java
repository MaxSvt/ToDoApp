package com.svt.todoapp.mapping;

import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.models.Project;
import com.svt.todoapp.models.Task;

public interface MapStructMapper {

    TaskDto toTaskDto(Task task);

    Task toTaskEntity(TaskCreationDto dto);

    ProjectDto toProjectDto(Project project);

    Project toProjectEntity(ProjectCreationDto dto);
}
