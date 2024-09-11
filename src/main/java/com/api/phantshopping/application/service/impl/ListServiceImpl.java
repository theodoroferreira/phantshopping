package com.api.phantshopping.application.service.impl;

import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.application.repository.ListRepository;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.phantshopping.domain.model.List;

@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {

    private final ListRepository repository;
    private final ModelMapper mapper;

    @Override
    public ListResponseDto create(ListRequestDto request) {
        List list = mapper.map(request, List.class);
        return mapper.map(repository.save(list), ListResponseDto.class);
    }

    @Override
    public java.util.List<List> findAll() {
        return repository.findAll();
    }
}
