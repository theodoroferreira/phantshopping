package com.api.phantshopping.framework.controller;

import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
import com.api.phantshopping.domain.model.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lists")
public class ListController {

    private final ListService service;

    @PostMapping("/create-list")
    public ResponseEntity<ListResponseDto> createList(@RequestBody @Validated ListRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping()
    public ResponseEntity<java.util.List<List>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

}
