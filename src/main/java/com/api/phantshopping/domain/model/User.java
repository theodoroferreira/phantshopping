package com.api.phantshopping.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private Long itemsAdded;
    private LocalDate entryDate;
    private Boolean active;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private java.util.List<List> lists;
}
