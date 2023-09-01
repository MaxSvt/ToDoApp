package com.svt.todoapp.dto.participant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ParticipantCreationDto {

    @NotBlank
    private String displayName;

    @NotBlank
    private String position;
}
