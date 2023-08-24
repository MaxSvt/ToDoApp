package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.comment.CommentCreationDto;
import com.svt.todoapp.dto.comment.CommentDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.Comment;
import com.svt.todoapp.models.Task;
import com.svt.todoapp.repositories.CommentRepository;
import com.svt.todoapp.repositories.TaskRepository;
import com.svt.todoapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final Mapper mapper;

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final TaskRepository taskRepository;

    @Override
    public void create(Long taskId, CommentCreationDto commentDto) {
        Task task = taskRepository.findById(taskId).orElse(null);
        Comment comment = mapper.toCommentEntity(commentDto);
        assert task != null;
        task.addComment(comment);
        commentRepository.save(comment);
    }

    @Override
    public CommentDto update(Long id, CommentCreationDto creationDto) {
        Comment comment = commentRepository.findById(id).orElse(null);
        assert comment != null;
        comment.setDescription(creationDto.getDescription());
        if(!comment.isUpdated()){
            comment.changeStatus(true);
        }
        commentRepository.save(comment);
        return mapper.toCommentDto(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
