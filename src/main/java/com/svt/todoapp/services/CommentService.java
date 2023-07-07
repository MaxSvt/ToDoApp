package com.svt.todoapp.services;

import com.svt.todoapp.dto.comment.CommentCreationDto;
import com.svt.todoapp.dto.comment.CommentDto;

public interface CommentService {

    void create(Long taskId, CommentCreationDto commentDto);

    CommentDto update(Long id, CommentCreationDto creationDto);

    void delete(Long id);
}
