package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.UserRepository;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import com.api.phantshopping.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    @Override
    public UserResponseDto create(UserRequestDto request) {
        User user = mapper.map(request, User.class);
        user.setActive(Boolean.TRUE);
        return mapper.map(repository.save(user), UserResponseDto.class);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
