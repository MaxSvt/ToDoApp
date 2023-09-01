package com.svt.todoapp.services.impl;

import com.svt.todoapp.repositories.ParticipantRepository;
import com.svt.todoapp.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private final ParticipantRepository participantRepository;

    @Override
    public void delete(Long id) {
        participantRepository.deleteById(id);
    }
}
