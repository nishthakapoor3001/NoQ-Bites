package com.example.noqbites.Request;

import com.example.noqbites.Enums.FoodCategoryEnum;
import com.example.noqbites.Enums.FoodTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FoodDto {
    @NotNull
    private String itemName;

    @NotNull
    private FoodTypeEnum itemType;

    @NotNull
    private FoodCategoryEnum itemCategory;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private Float price;
}
