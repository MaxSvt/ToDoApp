package com.svt.todoapp.services;

import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAll();

    ProjectDto getById(Long id);

    void create(ProjectCreationDto project, String username);

    ProjectDto update(Long id, ProjectCreationDto projectDto);

    void delete(Long id);
}
