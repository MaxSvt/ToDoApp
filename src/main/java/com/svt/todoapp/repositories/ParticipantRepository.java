package com.svt.todoapp.repositories;

import com.svt.todoapp.models.ProjectParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<ProjectParticipant, Long> {
}
