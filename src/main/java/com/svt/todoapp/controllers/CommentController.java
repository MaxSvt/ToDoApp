package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.comment.CommentCreationDto;
import com.svt.todoapp.dto.comment.CommentDto;
import com.svt.todoapp.services.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentServiceImpl commentService;

    @PostMapping(value = "/tasks/{taskId}/comments")
    public ResponseEntity<CommentDto>  create(@PathVariable(value = "taskId") Long taskId, @RequestBody CommentCreationDto dto){
        commentService.create(taskId, dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable(value = "id") Long id, @RequestBody CommentCreationDto dto){
        CommentDto postResponse = commentService.update(id, dto);
        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping(value = "/comments/{id}")
    public String deleteComment(@PathVariable(value = "id") Long id){
        commentService.delete(id);
        return "Comment has been deleted successfully";
    }
}
