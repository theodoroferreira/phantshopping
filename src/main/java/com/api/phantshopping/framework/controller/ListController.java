package com.api.phantshopping.framework.controller;

import com.api.phantshopping.application.service.ListService;
import com.api.phantshopping.domain.dto.request.ListRequestDto;
import com.api.phantshopping.domain.dto.response.ListResponseDto;
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
@RequestMapping("/lists")
public class ListController
{

    private final ListService service;

    @PostMapping("/create-list")
    @Operation(summary = "Create List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "List created successfully.",
                    content = @Content(schema = @Schema(implementation = ListResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<ListResponseDto> createList(@RequestBody @Valid ListRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    @Operation(summary = "Find All Lists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lists successfully found.",
                    content = @Content(schema = @Schema(implementation = ListResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Lists not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<java.util.List<ListResponseDto>> findAllLists()
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find List by Identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "List successfully found.",
                    content = @Content(schema = @Schema(implementation = ListResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "List not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<ListResponseDto> findListById(@PathVariable UUID id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findListById(id));
    }

    @PutMapping("/update-list/{id}")
    @Operation(summary = "Update List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "List updated successfully.",
                    content = @Content(schema = @Schema(implementation = ListResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "List not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<ListResponseDto> updateList(@PathVariable UUID id, @RequestBody @Validated ListRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateList(id, request));
    }

    @DeleteMapping("/delete-list/{id}")
    @Operation(summary = "Delete List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "List deleted successfully.",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<Void> deleteList(@PathVariable UUID id)
    {
        service.deleteList(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find-list-by-user/{userId}")
    @Operation(summary = "Find List by User Identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lists successfully found.",
                    content = @Content(schema = @Schema(implementation = ListResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Lists not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<java.util.List<ListResponseDto>> findListByUserId(@PathVariable UUID userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findListsByUserId(userId));
    }

}
