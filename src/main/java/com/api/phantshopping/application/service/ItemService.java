package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    ItemResponseDto create(ItemRequestDto list);

    java.util.List<ItemResponseDto> findAll();

    ItemResponseDto findItemById(UUID id);

    ItemResponseDto updateItem(UUID id, ItemRequestDto request);

    void deleteItem(UUID id);

    List<ItemResponseDto> findItemsByList(UUID listId);
}
