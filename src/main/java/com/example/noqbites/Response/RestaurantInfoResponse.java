package com.example.noqbites.Response;

import lombok.Data;

@Data
public class RestaurantInfoResponse extends BaseResponse{
    private String restaurantName;
    private String address;

    public RestaurantInfoResponse(String restaurantName, String address) {
        this.restaurantName = restaurantName;
        this.address = address;
    }

    public RestaurantInfoResponse() {
    }
}
