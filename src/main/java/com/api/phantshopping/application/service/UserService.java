package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService
{

    UserResponseDto createUser(UserRequestDto request);

    List<UserResponseDto> findAllUsers();

    UserResponseDto findUserById(UUID userId);

    UserResponseDto updateUser(UUID id, UserRequestDto request);

    void deleteUser(UUID id);

    void addListToUser(UUID userId, com.api.phantshopping.domain.model.List list);

    void addItem(UUID userId);
}
