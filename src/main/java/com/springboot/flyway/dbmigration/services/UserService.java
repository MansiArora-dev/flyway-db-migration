package com.springboot.flyway.dbmigration.services;

import com.springboot.flyway.dbmigration.dto.request.UserRequestDto;
import com.springboot.flyway.dbmigration.dto.response.UserResponseDto;
import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto createUser(UserRequestDto request);
    UserResponseDto updateUser(Long id, UserRequestDto request);
    void deleteUser(Long id);
}