package com.example.noqbites.DatabaseModels;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "ordered_date")
    private Date orderedDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Food product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer user;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;


    public Order() {
    }
}