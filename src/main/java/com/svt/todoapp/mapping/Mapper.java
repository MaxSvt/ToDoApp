package com.svt.todoapp.mapping;

import com.svt.todoapp.dto.comment.CommentCreationDto;
import com.svt.todoapp.dto.comment.CommentDto;
import com.svt.todoapp.dto.project.ProjectCreationDto;
import com.svt.todoapp.dto.project.ProjectDto;
import com.svt.todoapp.dto.project.ProjectSlimDto;
import com.svt.todoapp.dto.task.TaskCreationDto;
import com.svt.todoapp.dto.task.TaskDto;
import com.svt.todoapp.dto.task.TaskSlimDto;
import com.svt.todoapp.models.Comment;
import com.svt.todoapp.models.Project;
import com.svt.todoapp.models.Task;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper implements MapStructMapper {

    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private final String IS_UPDATED = "Изменен";

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
            commentDto.setIsUpdated(IS_UPDATED);
        } else{
            commentDto.setIsUpdated("Не изменен");
        }
        commentDto.setUpdatedDate(DATE_FORMAT.format(comment.getUpdatedDate()));
        return commentDto;
    }

    @Override
    public Comment toCommentEntity(CommentCreationDto dto) {
        if(dto.getDescription().isEmpty()){
            new NullPointerException().getMessage();
        }
        return new Comment(dto.getDescription());
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
