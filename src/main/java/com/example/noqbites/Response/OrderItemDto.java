package com.example.noqbites.Response;


import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {
    private Integer id;
    private Integer quantity;
    private FoodInfoResponseDto product;

    private Date orderedDate;

    public OrderItemDto() {
    }

    public OrderItemDto(Order order) {
        this.id = order.getId();
        this.quantity = order.getQuantity();
        this.orderedDate = order.getOrderedDate();
    }
}
