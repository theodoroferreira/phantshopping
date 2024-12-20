package com.api.phantshopping.framework.translate;

import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import com.api.phantshopping.domain.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public class UserTranslator {

    public UserResponseDto toResponse(@NotNull final User from) {
        return UserResponseDto.builder()
                .userId(from.getId())
                .name(from.getName())
                .email(from.getEmail())
                .itemsAdded(from.getItemsAdded())
                .entryDate(from.getEntryDate())
                .active(from.getActive())
                .lists(from.getLists()
                        .stream()
                        .map(list -> ListTranslator.builder().build().toResponse(list))
                        .collect(Collectors.toList()))
                .build();
    }

    public User fromRequestToEntity(@NotNull final UserRequestDto from) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(from.getName())
                .email(from.getEmail())
                .password(from.getPassword())
                .itemsAdded(0L)
                .entryDate(LocalDate.now())
                .active(Boolean.TRUE)
                .lists(Collections.emptyList())
                .build();
    }

    public User toEntity(@NotNull final UserResponseDto from) {
        return User.builder()
                .id(from.getUserId())
                .itemsAdded(from.getItemsAdded())
                .entryDate(from.getEntryDate())
                .active(from.getActive())
                .lists(from.getLists()
                        .stream()
                        .map(list -> ListTranslator.builder().build().toEntity(list))
                        .collect(Collectors.toList()))
                .build();
    }
}
