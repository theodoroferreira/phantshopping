package com.api.phantshopping.domain.dto.response;

import com.api.phantshopping.domain.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponseDto {

    private String listName;
    private List<Item> items;
}
