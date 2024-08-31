package com.example.noqbites.DatabaseModels;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "food")
public class Food {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer itemId;

    @NotNull
    @Column(name = "item_name")
    private String itemName;

    @NotNull
    @Column(name = "item_type")
    private Integer itemType;

    @NotNull
    @Column(name = "item_category")
    private Integer itemCategory;

    @NotNull
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @NotNull
    @Column(name = "price")
    private Float price;
}
