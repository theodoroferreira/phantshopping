package com.api.phantshopping.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {

    private UUID Id;
    private UUID listId;
    private String itemName;
    private String description;
    private Boolean purchased;
}
