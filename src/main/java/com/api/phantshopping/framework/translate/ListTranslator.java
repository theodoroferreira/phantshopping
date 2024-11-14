package com.api.phantshopping.framework.translate;

import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.List;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Collections;
import java.util.stream.Collectors;

@Builder
public class ListTranslator {

    public ListResponseDto toResponse(@NotNull final List from) {
        return ListResponseDto.builder()
                .listId(from.getId())
                .listName(from.getListName())
                .items(from.getItems()
                        .stream()
                        .map(item -> ItemTranslator.builder().build().toResponse(item))
                        .collect(Collectors.toList()))
                .build();
    }

    public List fromRequestToEntity(@NotNull final ListRequestDto from) {
        return List.builder()
                .listName(from.getListName())
                .items(Collections.emptyList())
                .build();

    }

    public List toEntity(@NotNull final ListResponseDto from) {
        return List.builder()
                .id(from.getListId())
                .listName(from.getListName())
                .items(from.getItems()
                        .stream()
                        .map(item -> ItemTranslator.builder().build().toEntity(item))
                        .collect(Collectors.toList()))
                .build();
    }
}
