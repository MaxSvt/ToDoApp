package com.svt.todoapp.services.impl;

import com.svt.todoapp.dto.user.RegistrationUserDto;
import com.svt.todoapp.dto.user.UpdateUserDto;
import com.svt.todoapp.dto.user.UserDto;
import com.svt.todoapp.mapping.Mapper;
import com.svt.todoapp.models.User;
import com.svt.todoapp.repositories.RoleRepository;
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

import java.util.List;
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
                String.format("User '%s' not found", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public List<UserDto> findAll(){
        return userRepository.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
    }

    public UserDto findById(Long id){
        return mapper.toUserDto(userRepository.findById(id).orElseThrow());
    }

    public UserDto updateUser(Long id, UpdateUserDto dto){
        User user = userRepository.findById(id).orElseThrow();
        return mapper.toUserDto(userRepository.save(mapper.toUpdatedUserDto(user, dto)));
    }

    public Optional<User> findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User createNewUser(RegistrationUserDto registrationUserDto){
        log.info("Saving new User with email: {}", registrationUserDto.getEmail());
        return userRepository.save(mapper.toUserEntity(registrationUserDto));
    }

    protected User splitDisplayName(String displayName){
        String[] firstnameAndLastname = displayName.split(" ");
        return userRepository.findByLastnameAndFirstname(firstnameAndLastname[0],
                firstnameAndLastname[1]).orElseThrow();
    }

}
