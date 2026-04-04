package com.springboot.flyway.dbmigration.services.impl;

import com.springboot.flyway.dbmigration.dto.request.UserRequestDto;
import com.springboot.flyway.dbmigration.dto.response.UserResponseDto;
import com.springboot.flyway.dbmigration.entities.User;
import com.springboot.flyway.dbmigration.exceptions.BadRequestException;
import com.springboot.flyway.dbmigration.exceptions.ResourceNotFoundException;
import com.springboot.flyway.dbmigration.repositories.UserRepository;
import com.springboot.flyway.dbmigration.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return toResponse(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists: " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return toResponse(userRepository.save(user));
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .createdAt(user.getCreatedAt())
                .build();
    }
}