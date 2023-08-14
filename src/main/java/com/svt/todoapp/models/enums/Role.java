package com.svt.todoapp.models.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN   ;

    @Override
    public String getAuthority() {
        return name();
    }
}
