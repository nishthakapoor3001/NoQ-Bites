package com.example.noqbites.Request;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RestaurantDto {
    private @Nonnull String restaurantName;

    private @Nonnull String password;

    private @Nonnull String address;
}
