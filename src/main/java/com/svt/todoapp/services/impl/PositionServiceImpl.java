package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.position.PositionCreationDto;
import com.svt.todoapp.dto.position.PositionDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.Position;
import com.svt.todoapp.repositories.PositionRepository;
import com.svt.todoapp.services.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionServiceImpl implements PositionService {

    @Autowired
    private final PositionRepository positionRepository;

    @Autowired
    private final Mapper mapper;

    @Override
    public List<PositionDto> getAll() {
        return positionRepository.findAll().stream().map(mapper::toPositionDto).collect(Collectors.toList());
    }

    @Override
    public PositionDto create(PositionCreationDto dto) {
        Position position = positionRepository.save(mapper.toPositionEntity(dto));
        return mapper.toPositionDto(position);
    }

    @Override
    public void delete(Integer id) {
        positionRepository.deleteById(id);
    }

    @Override
    public Position findByName(String name) {
        return positionRepository.findByName(name).orElseThrow();
    }
}
