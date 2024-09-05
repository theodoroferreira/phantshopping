package com.api.phantshopping.application.service;

import com.api.phantshopping.domain.dto.request.ListRequestDto;

import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.List;

public interface ListService {

    ListResponseDto create(ListRequestDto list);

    java.util.List<List> findAll();
}
