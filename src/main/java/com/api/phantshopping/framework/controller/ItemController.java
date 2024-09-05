package com.api.phantshopping.framework.controller;

import com.api.phantshopping.application.serviceImpl.ListServiceImpl;
import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.Item;
import com.api.phantshopping.domain.model.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemServiceImpl service;

    @PostMapping("/create-item")
    public ResponseEntity<ListResponseDto> createList(@RequestBody @Validated ItemRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping()
    public ResponseEntity<java.util.List<Item>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
}
