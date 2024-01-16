package com.example.noqbites.Request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartDto {

    private @NotNull  Integer id;
    private @NotNull Integer quantity;

    public AddToCartDto() {
    }

}
