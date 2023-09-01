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
import com.svt.todoapp.dto.user.UpdateUserDto;
import com.svt.todoapp.dto.user.UserDto;
import com.svt.todoapp.models.*;

public interface MapStructMapper {

    TaskDto toTaskDto(Task task);

    TaskSlimDto toTaskSlimDto(Task task);

    Task toTaskEntity(TaskCreationDto dto, User author, User performer);

    ProjectDto toProjectDto(Project project);

    ProjectSlimDto toProjectSlimDto(Project project);

    Project toProjectEntity(ProjectCreationDto dto, User user);

    CommentDto toCommentDto(Comment comment);

    Comment toCommentEntity(CommentCreationDto dto, User user);

    UserDto toUserDto(User user);

    User toUserEntity(RegistrationUserDto registrationUserDto);

    User toUpdatedUserDto(User user, UpdateUserDto userDto);

    ProjectParticipant toParticipantEntity(ParticipantCreationDto dto, Project project, User user, Position position);

    ParticipantDto toParticipantDto(ProjectParticipant participant);

    PositionDto toPositionDto(Position position);

    Position toPositionEntity(PositionCreationDto dto);
}
