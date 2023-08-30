package com.svt.todoapp.dto.participant;

import com.svt.todoapp.dto.position.PositionDto;
import com.svt.todoapp.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {
    private Long id;
    private UserDto employee;
    private PositionDto position;

}
