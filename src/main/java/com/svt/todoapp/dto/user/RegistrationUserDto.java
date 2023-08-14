package com.svt.todoapp.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationUserDto {
    private String username;
    private String password;
    private String email;
}
