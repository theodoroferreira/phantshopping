package com.api.phantshopping.framework.translate;

import com.api.phantshopping.domain.dto.request.ItemRequestDto;
import com.api.phantshopping.domain.dto.response.ItemResponseDto;
import com.api.phantshopping.domain.model.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class ItemTranslator
{

    public ItemResponseDto toResponse(@NotNull final Item from)
    {
        return ItemResponseDto.builder()
                .Id(from.getId())
                .itemName(from.getItemName())
                .description(from.getDescription())
                .listId(from.getList().getId())
                .purchased(from.getPurchased())
                .build();
    }

    public Item fromRequestToEntity(@NotNull final ItemRequestDto from)
    {
        return Item.builder()
                .itemName(from.getItemName())
                .description(from.getDescription())
                .purchased(from.getPurchased())
                .build();

    }

    public Item toEntity(@NotNull final ItemResponseDto from)
    {
        return Item.builder()
                .itemName(from.getItemName())
                .description(from.getDescription())
                .purchased(from.getPurchased())
                .build();
    }
}
