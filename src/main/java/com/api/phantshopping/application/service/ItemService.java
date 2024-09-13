package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;

public interface ItemService {

    ItemResponseDto create(ItemRequestDto list);

    java.util.List<ItemResponseDto> findAll();
}
