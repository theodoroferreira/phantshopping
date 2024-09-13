package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ListRepository;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.domain.model.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {

    private final ListRepository repository;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public ListResponseDto create(ListRequestDto request) {
        List list = mapper.map(request, List.class);
        userService.addListToUser(request.getUserId(), list);
        return mapper.map(repository.save(list), ListResponseDto.class);
    }

    @Override
    public java.util.List<ListResponseDto> findAll() {
        java.util.List<ListResponseDto> lists = new ArrayList<>();
        repository.findAll().forEach(list -> {
            lists.add(mapper.map(list, ListResponseDto.class));
        });
        return lists;
    }

    public List findByItemContains() {
        java.util.List<ItemResponseDto> items = new ArrayList<>();
        repository.findAll().forEach(item -> {
            items.add(mapper.map(item, ItemResponseDto.class));
        });
        return items;
    }

    @Override
    public void addItemToList(UUID listId, Item item) {
        List list = repository.findById(listId).get();

        if (item.getList().getListId() == list.getListId()) {
        }

        item.setList(list);
        list.getItems().add(item);

        userService.addItemToCount(list.getUser().getUserId());
        repository.save(list);
    }
}
