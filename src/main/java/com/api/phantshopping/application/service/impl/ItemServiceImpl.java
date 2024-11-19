package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ItemRepository;
import com.api.phantshopping.application.service.ItemService;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.framework.translate.ItemTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ListService listService;

    @Override
    public ItemResponseDto create(ItemRequestDto request) {
        Item item = repository.save(ItemTranslator.builder().build().fromRequestToEntity(request));
        listService.addItemToList(request.getListId(), item);
        return ItemTranslator.builder().build().toResponse(item);
    }

    @Override
    public java.util.List<ItemResponseDto> findAll() {
        java.util.List<ItemResponseDto> items = new ArrayList<>();
        repository.findAll().forEach(item -> {
            items.add(ItemTranslator.builder().build().toResponse(item));
        });
        return items;
    }

    @Override
    public ItemResponseDto findItemById(UUID id) {
        return ItemTranslator.builder().build().toResponse(repository.findById(id).get());
    }

    @Override
    public ItemResponseDto updateItem(UUID id, ItemRequestDto request) {
        Item item = repository.findById(id).get();

        item.setItemName(request.getItemName());
        item.setDescription(request.getDescription());

        repository.save(item);

        return ItemTranslator.builder().build().toResponse(item);
    }

    @Override
    public void deleteItem(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<ItemResponseDto> findItemsByList(UUID listId)
    {
        List<ItemResponseDto> items = new ArrayList<>();
        repository.findItemByListId(listId).forEach(item -> items.add(ItemTranslator.builder().build().toResponse(item)));
        return items;
    }
}
