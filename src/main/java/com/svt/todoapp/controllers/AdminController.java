package com.svt.todoapp.controllers;

import com.svt.todoapp.dto.user.UpdateUserDto;
import com.svt.todoapp.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

        @Autowired
        private final UserServiceImpl userService;

        @PostMapping(value = "/users/{id}")
        public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id, @RequestBody UpdateUserDto userDto){
            return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
        }
}
