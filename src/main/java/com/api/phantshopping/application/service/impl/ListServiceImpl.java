package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ListRepository;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.domain.model.List;
import com.api.phantshopping.framework.translate.ListTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService
{

    private final ListRepository repository;
    private final UserService userService;

    @Override
    public ListResponseDto create(ListRequestDto request)
    {
        List list = repository.save(ListTranslator.builder().build().fromRequestToEntity(request));
        userService.addListToUser(request.getUserId(), list);
        return ListTranslator.builder().build().toResponse(list);
    }

    @Override
    public java.util.List<ListResponseDto> findAll()
    {
        java.util.List<ListResponseDto> lists = new ArrayList<>();
        repository.findAll().forEach(list -> lists.add(ListTranslator.builder().build().toResponse(list)));
        return lists;
    }

    @Override
    public ListResponseDto findListById(UUID id)
    {
        return ListTranslator.builder().build().toResponse(repository.findById(id).get());
    }

    @Override
    public ListResponseDto updateList(UUID id, ListRequestDto request)
    {
        List list = repository.findById(id).get();

        list.setListName(request.getListName());

        repository.save(list);

        return ListTranslator.builder().build().toResponse(list);
    }

    @Override
    public void deleteList(UUID id)
    {
        repository.deleteById(id);
    }

    @Override
    public void addItemToList(UUID listId, Item item)
    {
        List list = repository.findById(listId).get();

        item.setList(list);
        list.getItems().add(item);
        repository.save(list);

        userService.addItem(list.getUser().getId());
    }

    @Override
    public java.util.List<ListResponseDto> findListByUserId(UUID userId)
    {
        java.util.List<ListResponseDto> lists = new ArrayList<>();
        repository.findListByUserId(userId).forEach(list -> lists.add(ListTranslator.builder().build().toResponse(list)));
        return lists;
    }
}
