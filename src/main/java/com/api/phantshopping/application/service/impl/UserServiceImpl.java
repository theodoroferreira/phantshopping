package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.UserRepository;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import com.api.phantshopping.domain.enums.RoleName;
import com.api.phantshopping.domain.model.List;
import com.api.phantshopping.domain.model.Role;
import com.api.phantshopping.domain.model.User;
import com.api.phantshopping.framework.config.security.SecurityConfig;
import com.api.phantshopping.framework.translate.UserTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final SecurityConfig securityConfig;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {
        User user = UserTranslator.builder().build().fromRequestToEntity(request, securityConfig
                .passwordEncoder()
                .encode(request.getPassword()));
        return UserTranslator.builder().build().toResponse(repository.save(user));
    }

    @Override
    public java.util.List<UserResponseDto> findAllUsers() {
        java.util.List<UserResponseDto> users = new ArrayList<>();
        repository.findAll().forEach(user -> {
            users.add(UserTranslator.builder().build().toResponse(user));
        });
        return users;
    }

    @Override
    public UserResponseDto findUserById(UUID id) {
        return UserTranslator.builder().build().toResponse(repository.findById(id).get());
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto request) {
        User user = repository.findById(id).get();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        repository.save(user);

        return UserTranslator.builder().build().toResponse(user);
    }

    @Override
    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void addListToUser(UUID userId, List list) {
        User user = repository.findById(userId).get();
        list.setUser(user);
        user.getLists().add(list);
        repository.save(user);
    }

    @Override
    public void addItem(UUID userId) {
        User user = repository.findById(userId).get();
        user.setItemsAdded(user.getItemsAdded() + 1);
        repository.save(user);
    }

    @Override
    public void subtractItem(UUID userId) {
        User user = repository.findById(userId).get();
        user.setItemsAdded(user.getItemsAdded() - 1);
        repository.save(user);
    }
}
