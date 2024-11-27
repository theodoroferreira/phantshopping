package com.api.phantshopping.framework.controller;

import com.api.phantshopping.application.service.ItemService;
import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController
{

    private final ItemService service;

    @PostMapping("/create-item")
    @Operation(summary = "Create Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item created successfully.",
                    content = @Content(schema = @Schema(implementation = ItemResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<ItemResponseDto> createList(@RequestBody @Valid ItemRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    @Operation(summary = "Find All Items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Items successfully found.",
                    content = @Content(schema = @Schema(implementation = ItemResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Items not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<java.util.List<ItemResponseDto>> findAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Item by Identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item successfully found.",
                    content = @Content(schema = @Schema(implementation = ItemResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Item not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<ItemResponseDto> findItemById(@PathVariable UUID id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findItemById(id));
    }

    @PutMapping("update-item/{id}")
    @Operation(summary = "Update Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item updated successfully.",
                    content = @Content(schema = @Schema(implementation = ItemResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Item not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable UUID id, @RequestBody @Validated ItemRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateItem(id, request));
    }

    @DeleteMapping("delete-item/{id}")
    @Operation(summary = "Delete Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item deleted successfully.",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id)
    {
        service.deleteItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find-by-list/{listId}")
    @Operation(summary = "Find Items by List Identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Items successfully found.",
                    content = @Content(schema = @Schema(implementation = ItemResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Items not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<java.util.List<ItemResponseDto>> findItemsByList(@PathVariable UUID listId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findItemsByList(listId));
    }
}
