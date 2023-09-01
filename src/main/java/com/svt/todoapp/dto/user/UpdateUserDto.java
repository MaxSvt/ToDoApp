package com.svt.todoapp.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String email;
    private String firstname;
    private String lastname;
}
