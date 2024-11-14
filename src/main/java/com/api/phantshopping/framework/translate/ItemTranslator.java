package com.api.phantshopping.framework.translate;

import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class ItemTranslator {

    public ItemResponseDto toResponse(@NotNull final Item from) {
        return ItemResponseDto.builder()
                .itemName(from.getItemName())
                .description(from.getDescription())
                .build();
    }

    public Item fromRequestToEntity(@NotNull final ItemRequestDto from) {
        return Item.builder()
                .itemName(from.getItemName())
                .description(from.getDescription())
                .purchased(Boolean.FALSE)
                .build();

    }

    public Item toEntity(@NotNull final ItemResponseDto from) {
        return Item.builder()
                .itemName(from.getItemName())
                .description(from.getDescription())
                .purchased(Boolean.FALSE)
                .build();
    }
}
