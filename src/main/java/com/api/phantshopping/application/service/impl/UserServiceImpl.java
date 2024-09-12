package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.UserRepository;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import com.api.phantshopping.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public List<UserResponseDto> findAll() {
        List<UserResponseDto> list = new ArrayList<>();
        repository.findAll().forEach(user -> {
            list.add(mapper.map(user, UserResponseDto.class));
        });
        return list;
    }

    @Override
    public UserResponseDto findUserById(UUID id) {
        return mapper.map(repository.findById(id), UserResponseDto.class);
    }

    @Override
    public com.api.phantshopping.domain.model.List addListToUser(UUID userId, com.api.phantshopping.domain.model.List list) {
        User user = repository.findById(userId).get();

        list.setUser(user);
        user.getLists().add(list);

        repository.save(user);
        return list;
    }
}
