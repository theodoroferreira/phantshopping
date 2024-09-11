package com.api.phantshopping.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    private String itemTitle;
    private String description;
    private Boolean purchased;
}
