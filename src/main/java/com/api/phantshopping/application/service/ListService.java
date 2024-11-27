package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;

import java.util.UUID;

public interface ListService
{

    ListResponseDto create(ListRequestDto list);

    java.util.List<ListResponseDto> findAll();

    void addItemToList(UUID listId, Item item);

    ListResponseDto findListById(UUID id);

    ListResponseDto updateList(UUID id, ListRequestDto request);

    void deleteList(UUID id);

    java.util.List<ListResponseDto> findListsByUserId(UUID userId);
}
