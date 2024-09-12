package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.domain.model.List;

import java.util.UUID;

public interface ListService {

    ListResponseDto create(ListRequestDto list);

    java.util.List<List> findAll();

    Item addItemToList(UUID listId, Item item);
}
