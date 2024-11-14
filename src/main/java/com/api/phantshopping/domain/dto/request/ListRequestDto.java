package com.api.phantshopping.domain.dto.request;

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
public class ListRequestDto {

    @NotNull(message = "listName is required")
    private String listName;
    @NotNull(message = "userId is required")
    private UUID userId;
}
