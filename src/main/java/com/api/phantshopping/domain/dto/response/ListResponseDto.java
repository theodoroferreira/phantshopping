package com.api.phantshopping.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto {

    private UUID listId;
    private String listName;
    private List<ItemResponseDto> items;
}
