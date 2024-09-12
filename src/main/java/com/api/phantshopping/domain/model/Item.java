package com.api.phantshopping.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID itemId;
    private String itemName;
    private String description;
    private Boolean purchased;
    @ManyToOne
    @JoinColumn(name = "list_id")
    @JsonBackReference
    private List list;

}
