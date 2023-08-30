package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.participant.ParticipantCreationDto;
import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.Position;
import com.svt.todoapp.models.Project;
import com.svt.todoapp.models.ProjectParticipant;
import com.svt.todoapp.models.User;
import com.svt.todoapp.repositories.ParticipantRepository;
import com.svt.todoapp.repositories.ProjectRepository;
import com.svt.todoapp.repositories.UserRepository;
import com.svt.todoapp.services.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private final Mapper mapper;

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final PositionServiceImpl positionService;

    @Autowired
    private final ParticipantRepository participantRepository;

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream().map(mapper::toProjectDto).collect(Collectors.toList());
    }

    @Override
    public ProjectDto getById(Long id) {
        return mapper.toProjectDto(Objects.requireNonNull(projectRepository.findById(id).orElse(null)));
    }

    @Override
    public ProjectDto create(ProjectCreationDto projectDto, String username) {
        User user = userService.findByUserName(username).orElseThrow();
        Project project = mapper.toProjectEntity(projectDto, user);
        return mapper.toProjectDto(projectRepository.save(project));
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

    @Override
    public ProjectDto addEmployee(Long id, ParticipantCreationDto dto) {
        ProjectParticipant participant = new ProjectParticipant();
        Project project = projectRepository.findById(id).orElseThrow();
        participant.setProject(project);
        participant.setEmployee(userService.splitDisplayName(dto.getDisplayName()));
        participant.setPosition(positionService.findByName(dto.getPosition()));
        participantRepository.save(participant);
        return mapper.toProjectDto(project);
    }
}
