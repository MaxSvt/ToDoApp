package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.jwt.JwtRequest;
import com.svt.todoapp.dto.jwt.JwtResponse;
import com.svt.todoapp.dto.user.RegistrationUserDto;
import com.svt.todoapp.exceptions.AppError;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.User;
import com.svt.todoapp.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final Mapper mapper;

    public ResponseEntity<?> createAuthToken(JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createUser(RegistrationUserDto registrationUserDto){
        if(userService.findByEmail(registrationUserDto.getEmail()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User exists"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(mapper.toUserDto(user));
    }
}
