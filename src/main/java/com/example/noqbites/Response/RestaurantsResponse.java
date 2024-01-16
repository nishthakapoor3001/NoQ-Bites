package com.example.noqbites.Response;

import lombok.Data;

import java.util.List;

@Data
public class RestaurantsResponse extends BaseResponse {

    List<RestaurantInfoResponse> restaurants;

}
