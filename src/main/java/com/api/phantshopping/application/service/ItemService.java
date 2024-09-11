package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.model.Item;

public interface ItemService {

    ItemResponseDto create(ItemRequestDto list);

    java.util.List<Item> findAll();
}
