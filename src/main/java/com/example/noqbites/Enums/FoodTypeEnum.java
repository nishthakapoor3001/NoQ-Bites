package com.example.noqbites.Enums;

import java.util.HashMap;
import java.util.Map;

public enum FoodTypeEnum {
    VEG(1),
    NON_VEG(2);

    private int value;
    private static Map map = new HashMap<>();

    private FoodTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (FoodTypeEnum pageType : FoodTypeEnum.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static FoodTypeEnum valueOf(int pageType) {
        return (FoodTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
