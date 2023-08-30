package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.position.PositionCreationDto;
import com.svt.todoapp.dto.position.PositionDto;
import com.svt.todoapp.services.impl.PositionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    @Autowired
    private final PositionServiceImpl positionService;

    @GetMapping
    public Iterable<PositionDto> getAll(){
        return positionService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PositionCreationDto dto){
        return new ResponseEntity<>(positionService.create(dto), HttpStatus.CREATED);
    }
}
