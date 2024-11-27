package com.api.phantshopping.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lists")
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String listName;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @JsonManagedReference
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private java.util.List<Item> items;
}
