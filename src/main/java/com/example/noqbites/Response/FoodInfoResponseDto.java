package com.example.noqbites.Response;

import com.example.noqbites.Enums.FoodCategoryEnum;
import com.example.noqbites.Enums.FoodTypeEnum;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodInfoResponseDto extends BaseResponse{
    private int foodId;

    private String foodItem;

    private FoodTypeEnum foodType;

    private FoodCategoryEnum foodCategory;

    private Float price;
}
