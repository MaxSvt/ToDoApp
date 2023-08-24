package com.svt.todoapp.mapping;

import com.svt.todoapp.dto.comment.CommentCreationDto;
import com.svt.todoapp.dto.comment.CommentDto;
import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.dto.project.ProjectSlimDto;
import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.TaskSlimDto;
import com.svt.todoapp.dto.user.RegistrationUserDto;
import com.svt.todoapp.dto.user.UserDto;
import com.svt.todoapp.models.Comment;
import com.svt.todoapp.models.Project;
import com.svt.todoapp.models.Task;
import com.svt.todoapp.models.User;
import com.svt.todoapp.models.enums.Role;
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

    public Mapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TaskDto toTaskDto(Task task){
        if(task == null){
            return null;
        }
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().getTitle(),
                DATE_FORMAT.format(task.getCreatedDate()),
                DATE_FORMAT.format(task.getChangedDate()),
                toProjectSlimDto(task.getProject()),
                commentDtoList(task.getComments())

        );
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
    public Task toTaskEntity(TaskCreationDto dto){
        // ДОБАВИТЬ ИСКЛЮЧЕНИЕ!!!
        if(dto.getTitle().isEmpty() || dto.getDescription().isEmpty()){
            new NullPointerException().getMessage();
        }
        return new Task(dto.getTitle(), dto.getDescription());
    }

    @Override
    public ProjectDto toProjectDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getStatus().getTitle(),
                taskDtoList(project.getTasks())
        );
    }

    @Override
    public ProjectSlimDto toProjectSlimDto(Project project) {
        return new ProjectSlimDto(
                project.getId(),
                project.getTitle(),
                project.getStatus().getTitle()
        );
    }

    @Override
    public Project toProjectEntity(ProjectCreationDto dto) {
        if(dto.getTitle().isEmpty() || dto.getDescription().isEmpty()){
            new NullPointerException().getMessage();
        }
        return new Project(dto.getTitle(), dto.getDescription());
    }

    @Override
    public CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setDescription(comment.getDescription());
        commentDto.setCreationDate(DATE_FORMAT.format(comment.getCreatedDate()));
        if(comment.isUpdated()){
            commentDto.setUpdated(true);
        }
        return commentDto;
    }

    @Override
    public Comment toCommentEntity(CommentCreationDto dto) {
        if(dto.getDescription().isEmpty()){
            new NullPointerException().getMessage();
        }
        return new Comment(dto.getDescription());
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
        user.getRoles().add(Role.ROLE_ADMIN);
        user.setActive(true);
        user.setCreatedDate(LocalDateTime.now());
        return user;
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
            displayStringJoiner.add("[X](Неактивный)");
        }
        return displayStringJoiner.toString();
    }

    protected List<TaskSlimDto> taskDtoList(List<Task> list){
        if(list.isEmpty()){
            return null;
        }
        List<TaskSlimDto> dtoList = new ArrayList<>();
        for(Task task : list){
            dtoList.add(toTaskSlimDto(task));
        }
        return dtoList;
    }

    protected List<CommentDto> commentDtoList(List<Comment> list){
        if(list.isEmpty()){
            return null;
        }
        List<CommentDto> dtoList = new ArrayList<>();
        for(Comment comment : list){
            dtoList.add(toCommentDto(comment));
        }
        return dtoList;
    }
}
