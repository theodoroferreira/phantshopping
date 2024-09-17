package com.api.phantshopping.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lists")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID listId;
    private String listName;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
    private java.util.List<Item> items;
}
