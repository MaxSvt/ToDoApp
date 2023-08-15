package com.svt.todoapp.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationUserDto {
    private String email;
    private String firstname;
    private String lastname;
    private String password;
}
