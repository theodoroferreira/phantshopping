package com.api.phantshopping.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private UUID userId;
    private String name;
    private String email;
    private Long itemsAdded;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate entryDate;
    private Boolean active;
    private java.util.List<ListResponseDto> lists;
}
