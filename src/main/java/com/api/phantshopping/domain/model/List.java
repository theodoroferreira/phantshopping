package com.api.phantshopping.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private java.util.List<Item> items;
}
