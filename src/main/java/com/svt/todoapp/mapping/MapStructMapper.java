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

public interface MapStructMapper {

    TaskDto toTaskDto(Task task);

    TaskSlimDto toTaskSlimDto(Task task);

    Task toTaskEntity(TaskCreationDto dto);

    ProjectDto toProjectDto(Project project);

    ProjectSlimDto toProjectSlimDto(Project project);

    Project toProjectEntity(ProjectCreationDto dto, User user);

    CommentDto toCommentDto(Comment comment);

    Comment toCommentEntity(CommentCreationDto dto);

    UserDto toUserDto(User user);

    User toUserEntity(RegistrationUserDto registrationUserDto);
}
