package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ItemRepository;
import com.api.phantshopping.application.service.ItemService;
import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ModelMapper mapper;

    @Override
    public ItemResponseDto create(ItemRequestDto request) {
        request.setPurchased(Boolean.FALSE);
        Item item = mapper.map(request, Item.class);
        return mapper.map(repository.save(item), ItemResponseDto.class);
    }

    @Override
    public List<Item> findAll() {
        return repository.findAll();
    }
}