package com.example.noqbites.DatabaseModels;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "Restaurant")
public class Restaurant {
    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Integer restaurantId;

    @Column(name = "restaurant_name")
    private @Nonnull String restaurantName;

    @Column(name = "password")
    private @Nonnull String password;

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "address")
    private @Nonnull String address;

    public Restaurant() {
    }

    public Restaurant(String name, String password, String address) {
        this.restaurantName = name;
        this.address = address;
        this.password = password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getAddress() {
        return address;
    }
}
