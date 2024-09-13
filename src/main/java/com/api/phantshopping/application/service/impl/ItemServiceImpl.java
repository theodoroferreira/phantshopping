package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ItemRepository;
import com.api.phantshopping.application.service.ItemService;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ListService listService;
    private final ModelMapper mapper;

    @Override
    public ItemResponseDto create(ItemRequestDto request) {
        Item item = mapper.map(request, Item.class);
        item.setPurchased(Boolean.FALSE);
        listService.addItemToList(request.getListId(), item);
        return mapper.map(repository.save(item), ItemResponseDto.class);
    }

    @Override
    public List<ItemResponseDto> findAll() {
        List<ItemResponseDto> items = new ArrayList<>();
        repository.findAll().forEach(item -> {
            items.add(mapper.map(item, ItemResponseDto.class));
        });
        return items;
    }
}
