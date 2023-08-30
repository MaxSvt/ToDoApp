package com.svt.todoapp.services;

import com.svt.todoapp.dto.participant.ParticipantCreationDto;
import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.models.Project;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAll();

    ProjectDto getById(Long id);

    ProjectDto create(ProjectCreationDto project, String username);

    ProjectDto update(Long id, ProjectCreationDto projectDto);

    void delete(Long id);

    ProjectDto addEmployee(Long id, ParticipantCreationDto dto);
}
