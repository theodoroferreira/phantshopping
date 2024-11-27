package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ItemRepository;
import com.api.phantshopping.application.repository.ListRepository;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.domain.model.List;
import com.api.phantshopping.framework.exeption.GenericException;
import com.api.phantshopping.framework.translate.ListTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {
    private final ListRepository listRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public ListResponseDto create(ListRequestDto request) {
        List list = ListTranslator.builder().build().fromRequestToEntity(request);
        userService.addListToUser(request.getUserId(), list);
        return ListTranslator.builder().build().toResponse(list);
    }

    @Override
    public java.util.List<ListResponseDto> findAll() {
        java.util.List<ListResponseDto> lists = new ArrayList<>();
        listRepository.findAll().forEach(list -> lists.add(ListTranslator.builder().build().toResponse(list)));
        return lists;
    }

    @Override
    public ListResponseDto findListById(UUID id) {
        return ListTranslator.builder().build().toResponse(listRepository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "List not found.")));
    }

    @Override
    public ListResponseDto updateList(UUID id, ListRequestDto request) {
        List list = listRepository.findById(id).orElseThrow(() -> new GenericException(HttpStatus.BAD_REQUEST, "List not found."));

        list.setListName(request.getListName());

        listRepository.save(list);

        return ListTranslator.builder().build().toResponse(list);
    }

    @Override
    public void deleteList(UUID id) {
        itemRepository.deleteItemsByListId(id);
        listRepository.deleteListByIdNative(id);
    }

    @Override
    public void addItemToList(UUID listId, Item item) {
        List list = listRepository.findById(listId).get();

        item.setList(list);
        list.getItems().add(item);
        listRepository.save(list);

        userService.addItem(list.getUser().getId());
    }

    @Override
    public java.util.List<ListResponseDto> findListsByUserId(UUID userId) {
        java.util.List<ListResponseDto> lists = new ArrayList<>();
        listRepository.findListByUserId(userId).forEach(list -> lists.add(ListTranslator.builder().build().toResponse(list)));
        return lists;
    }
}
