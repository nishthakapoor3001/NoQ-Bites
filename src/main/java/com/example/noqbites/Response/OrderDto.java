package com.example.noqbites.Response;


import com.example.noqbites.DatabaseModels.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends BaseResponse{
    private List<OrderItemDto> orderItems;
    private double totalCost;

    public OrderDto() {
    }
}
