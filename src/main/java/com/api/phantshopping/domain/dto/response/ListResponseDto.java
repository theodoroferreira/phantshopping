package com.api.phantshopping.domain.dto.response;

import com.api.phantshopping.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto {

    private String listName;
    private java.util.List<Item> items;
}
