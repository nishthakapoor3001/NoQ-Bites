package com.example.noqbites.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodsByRestaurantResponseDto extends BaseResponse{
    List<FoodInfoResponseDto> food;
}
