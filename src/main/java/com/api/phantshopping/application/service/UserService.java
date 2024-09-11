package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import com.api.phantshopping.domain.model.User;

import java.util.List;

public interface UserService {

    UserResponseDto create(UserRequestDto user);

    List<User> findAll();
}
