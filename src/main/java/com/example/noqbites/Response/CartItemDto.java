package com.example.noqbites.Response;


import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Food;

public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private FoodInfoResponseDto product;

    public CartItemDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public FoodInfoResponseDto getProduct() {
        return product;
    }

    public void setProduct(FoodInfoResponseDto product) {
        this.product = product;
    }

    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
    }
}
