package com.api.phantshopping.framework.controller;

import com.api.phantshopping.application.service.UserService;
import com.api.phantshopping.domain.dto.request.UserRequestDto;
import com.api.phantshopping.domain.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController
{

    private final UserService service;

    @PostMapping("/create-user")
    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Validated UserRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(request));
    }

    @GetMapping
    @Operation(summary = "Find All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users successfully found.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "Users not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<java.util.List<UserResponseDto>> findAllUsers()
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find User by Identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully found.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable UUID id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.findUserById(id));
    }

    @PutMapping("/update-user/{id}")
    @Operation(summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "404", description = "User not found."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody @Validated UserRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(id, request));
    }

    @DeleteMapping("/delete-user/{id}")
    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully.",
                    content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request."),
            @ApiResponse(responseCode = "500", description = "Internal error.")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id)
    {
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
