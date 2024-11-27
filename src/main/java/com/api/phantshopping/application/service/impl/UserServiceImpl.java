package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ItemRepository;
import com.api.phantshopping.application.repository.ListRepository;
import com.api.phantshopping.application.repository.RoleRepository;
import com.api.phantshopping.application.repository.UserRepository;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import com.api.phantshopping.domain.enums.RoleName;
import com.api.phantshopping.domain.model.List;
import com.api.phantshopping.domain.model.Role;
import com.api.phantshopping.domain.model.User;
import com.api.phantshopping.framework.config.security.SecurityConfig;
import com.api.phantshopping.framework.exeption.GenericException;
import com.api.phantshopping.framework.translate.UserTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ListRepository listRepository;
    private final ItemRepository itemRepository;

    private final SecurityConfig securityConfig;

    @Override
    public UserResponseDto createUser(UserRequestDto request)
    {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Email already in use: " + user.getEmail());
        });

        User user = UserTranslator.builder().build().fromRequestToEntity(request, securityConfig
                .passwordEncoder()
                .encode(request.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("Role not found"));
        user.setRoles(Collections.singletonList(userRole));

        return UserTranslator.builder().build().toResponse(userRepository.save(user));
    }

    @Override
    public java.util.List<UserResponseDto> findAllUsers()
    {
        java.util.List<UserResponseDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            users.add(UserTranslator.builder().build().toResponse(user));
        });
        return users;
    }

    @Override
    public UserResponseDto findUserById(UUID id)
    {
        return UserTranslator.builder().build().toResponse(userRepository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "User not found.")));
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto request)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "User not found."));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return UserTranslator.builder().build().toResponse(user);
    }

    @Override
    public void deleteUser(UUID id)
    {
        userRepository.findById(id).get().getLists().forEach(list -> {
            itemRepository.deleteItemsByListId(list.getId());
        });
        listRepository.deleteListsByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public void addListToUser(UUID userId, List list)
    {
        User user = userRepository.findById(userId).get();
        list.setUser(user);
        user.getLists().add(list);
        userRepository.save(user);
    }

    @Override
    public void addItem(UUID userId)
    {
        User user = userRepository.findById(userId).get();
        user.setItemsAdded(user.getItemsAdded() + 1);
        userRepository.save(user);
    }
}
