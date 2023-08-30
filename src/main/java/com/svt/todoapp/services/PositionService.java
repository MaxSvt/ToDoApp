package com.svt.todoapp.services;


import com.svt.todoapp.dto.position.PositionCreationDto;
import com.svt.todoapp.dto.position.PositionDto;
import com.svt.todoapp.models.Position;

import java.util.List;

public interface PositionService {

    List<PositionDto> getAll();

    PositionDto create(PositionCreationDto dto);

    void delete(Integer id);

    Position findByName(String name);
}
