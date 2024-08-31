package com.example.noqbites.Enums;

import java.util.HashMap;
import java.util.Map;

public enum FoodCategoryEnum {
    BEVERAGE(1),
    NON_BEVERAGE(2);

    private int value;
    private static Map map = new HashMap<>();

    private FoodCategoryEnum(int value) {
        this.value = value;
    }

    static {
        for (FoodCategoryEnum pageType : FoodCategoryEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static FoodCategoryEnum valueOf(int pageType) {
        return (FoodCategoryEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
