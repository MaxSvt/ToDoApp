package com.svt.todoapp.mapping;

import com.svt.todoapp.dto.comment.CommentCreationDto;
import com.svt.todoapp.dto.comment.CommentDto;
import com.svt.todoapp.dto.participant.ParticipantCreationDto;
import com.svt.todoapp.dto.participant.ParticipantDto;
import com.svt.todoapp.dto.position.PositionCreationDto;
import com.svt.todoapp.dto.position.PositionDto;
import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.dto.project.ProjectSlimDto;
import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.TaskSlimDto;
import com.svt.todoapp.dto.user.RegistrationUserDto;
import com.svt.todoapp.dto.user.UserDto;
import com.svt.todoapp.models.*;
import com.svt.todoapp.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Component
public class Mapper implements MapStructMapper {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Mapper(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public TaskDto toTaskDto(Task task){
        if(task == null){
            return null;
        }
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus().getTitle());
        dto.setAuthor(generateDisplayName(task.getAuthor().getFirstname(),
                task.getAuthor().getLastname(), task.getAuthor().isActive()));
        dto.setPerformer(generateDisplayName(task.getPerformer().getFirstname(),
                task.getPerformer().getLastname(), task.getPerformer().isActive()));
        dto.setCreatedDate(DATE_FORMAT.format(task.getCreatedDate()));
        dto.setChangedDate(DATE_FORMAT.format(task.getChangedDate()));
        dto.setProject(toProjectSlimDto(task.getProject()));
        dto.setComments(commentDtoList(task.getComments()));
        return dto;
    }

    @Override
    public TaskSlimDto toTaskSlimDto(Task task) {
        return new TaskSlimDto(
                task.getId(),
                task.getTitle(),
                task.getStatus().getTitle()
        );
    }

    @Override
    public Task toTaskEntity(TaskCreationDto dto, User author, User performer){
        return new Task(dto.getTitle(), dto.getDescription(), author, performer);
    }

    @Override
    public ProjectDto toProjectDto(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setCode(project.getCode());
        dto.setDescription(project.getDescription());
        dto.setProjectManager(generateDisplayName(project.getProjectManager().getFirstname(),
                project.getProjectManager().getLastname(), project.getProjectManager().isActive()));
        dto.setStatus(project.getStatus().getTitle());
        dto.setTasks(taskDtoList(project.getTasks()));
        dto.setParticipants(participantDtoList(project.getParticipants()));
        return dto;
    }

    @Override
    public ProjectSlimDto toProjectSlimDto(Project project) {
        return new ProjectSlimDto(
                project.getId(),
                project.getCode(),
                project.getTitle(),
                project.getStatus().getTitle()
        );
    }

    @Override
    public Project toProjectEntity(ProjectCreationDto dto, User user) {
        return new Project(dto.getTitle(), dto.getCode(), dto.getDescription(), user);
    }

    @Override
    public CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setDescription(comment.getDescription());
        commentDto.setAuthor(generateDisplayName(comment.getAuthor().getFirstname(),
                comment.getAuthor().getLastname(), comment.getAuthor().isActive()));
        commentDto.setCreationDate(DATE_FORMAT.format(comment.getCreatedDate()));
        commentDto.setUpdated(comment.isUpdated());
        return commentDto;
    }

    @Override
    public Comment toCommentEntity(CommentCreationDto dto, User user) {
        return new Comment(dto.getDescription(), user);
    }

    @Override
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                generateDisplayName(user.getFirstname(), user.getLastname(), user.isActive()),
                user.isActive()
        );
    }

    @Override
    public User toUserEntity(RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setEmail(registrationUserDto.getEmail());
        user.setFirstname(registrationUserDto.getFirstname());
        user.setLastname(registrationUserDto.getLastname());
        user.setUsername(generateUsername(registrationUserDto.getEmail()));
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        user.setActive(true);
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }

    @Override
    public ProjectParticipant toParticipantEntity(ParticipantCreationDto dto, Project project, User user, Position position) {
        ProjectParticipant participant = new ProjectParticipant();
        participant.setProject(project);
        participant.setEmployee(user);
        participant.setPosition(position);
        return participant;
    }

    @Override
    public ParticipantDto toParticipantDto(ProjectParticipant participant) {
        ParticipantDto dto = new ParticipantDto();
        dto.setId(participant.getId());
        dto.setEmployee(toUserDto(participant.getEmployee()));
        dto.setPosition(toPositionDto(participant.getPosition()));
        return dto;
    }

    @Override
    public PositionDto toPositionDto(Position position) {
        PositionDto dto = new PositionDto();
        dto.setId(position.getId());
        dto.setName(position.getName());
        return dto;
    }

    @Override
    public Position toPositionEntity(PositionCreationDto dto) {
        Position position = new Position();
        position.setName(dto.getName());
        return position;
    }

    protected String generateUsername(String email){
        String[] emailParts = email.split("@");
        return emailParts[0];
    }

    protected String generateDisplayName(String firstname, String lastname, boolean isActive){
        StringJoiner displayStringJoiner = new StringJoiner(" ");
        if(firstname != null){
            displayStringJoiner.add(lastname);
        }
        if(lastname != null){
            displayStringJoiner.add(firstname);
        }
        if(!isActive){
            displayStringJoiner.add("[X] (Неактивный)");
        }
        return displayStringJoiner.toString();
    }

    protected List<TaskSlimDto> taskDtoList(List<Task> list){
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        List<TaskSlimDto> dtoList = new ArrayList<>();
        for(Task task : list){
            dtoList.add(toTaskSlimDto(task));
        }
        return dtoList;
    }

    protected List<CommentDto> commentDtoList(List<Comment> list){
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        List<CommentDto> dtoList = new ArrayList<>();
        for(Comment comment : list){
            dtoList.add(toCommentDto(comment));
        }
        return dtoList;
    }

    protected List<ParticipantDto> participantDtoList(List<ProjectParticipant> list){
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        List<ParticipantDto> dtoList = new ArrayList<>();
        for(ProjectParticipant participant : list){
            dtoList.add(toParticipantDto(participant));
        }
        return dtoList;
    }
}
