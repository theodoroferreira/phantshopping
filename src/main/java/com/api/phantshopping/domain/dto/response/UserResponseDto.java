package com.api.phantshopping.domain.dto.response;

import com.api.phantshopping.domain.model.List;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String name;
    @Email
    private String email;
    private Long itemsAdded;
    private LocalDate entryDate;
    private Boolean active;
    private java.util.List<List> lists;
}
