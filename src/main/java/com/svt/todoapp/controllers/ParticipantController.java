package com.svt.todoapp.controllers;

import com.svt.todoapp.services.impl.ParticipantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/participants")
@RequiredArgsConstructor
public class ParticipantController {

    @Autowired
    private final ParticipantServiceImpl participantService;

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
        participantService.delete(id);
    }
}
