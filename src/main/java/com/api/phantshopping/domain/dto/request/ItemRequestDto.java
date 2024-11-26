package com.api.phantshopping.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    @NotBlank(message = "Item name is required.")
    private String itemName;
    @NotBlank(message = "Item description is required.")
    private String description;
    private UUID listId;
}
