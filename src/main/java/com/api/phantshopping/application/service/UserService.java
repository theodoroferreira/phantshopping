package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto create(UserRequestDto request);

    List<UserResponseDto> findAll();

    UserResponseDto findUserById(UUID userId);

    void addListToUser(UUID userId, com.api.phantshopping.domain.model.List list);

    void addItemToCount(UUID userId);
}
