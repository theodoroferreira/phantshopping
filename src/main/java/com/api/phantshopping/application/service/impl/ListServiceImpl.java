package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.repository.ListRepository;
import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.domain.model.List;
import com.api.phantshopping.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public java.util.List<List> findAll() {
        return repository.findAll();
    }

    @Override
    public Item addItemToList(UUID listId, Item item) {
        List list = repository.findById(listId).get();

        item.setList(list);
        list.getItems().add(item);

        repository.save(list);
        return item;
    }
}
