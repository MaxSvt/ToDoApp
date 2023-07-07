package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.Project;
import com.svt.todoapp.repositories.ProjectRepository;
import com.svt.todoapp.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private final Mapper mapper;

    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream().map(mapper::toProjectDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto getById(Long id) {
        return mapper.toProjectDto(Objects.requireNonNull(projectRepository.findById(id).orElse(null)));
    }

    @Override
    public void create(ProjectCreationDto project) {
        projectRepository.save(mapper.toProjectEntity(project));
    }

    @Override
    public ProjectDto update(Long id, ProjectCreationDto projectDto) {
        Project project = projectRepository.findById(id).orElse(null);
        assert project != null;
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        projectRepository.save(project);
        return mapper.toProjectDto(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
