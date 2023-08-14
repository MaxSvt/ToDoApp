package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.user.RegistrationUserDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.User;
import com.svt.todoapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final Mapper mapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username).orElseThrow(()-> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList())
        );
    }

    public Optional<User> findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User createNewUser(RegistrationUserDto registrationUserDto){
        log.info("Saving new User with email: {}", registrationUserDto.getEmail());
        return userRepository.save(mapper.toUserEntity(registrationUserDto));
    }

}
