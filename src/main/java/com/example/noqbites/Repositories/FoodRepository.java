package com.example.noqbites.Repositories;

import com.example.noqbites.DatabaseModels.Food;
import com.example.noqbites.DatabaseModels.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer > {

    @Query(value = "select f from Food f where f.itemName=:x and f.itemCategory=:y and f.itemType=:z and f.restaurantId=:a")
    Food getFood(@Param("x") String name, @Param("y") int category, @Param("z") int type, @Param("a") int resId);

    @Query(value = "select f from Food f where f.itemName=:x")
    Food getFoodByName(@Param("x") String name);

    @Query(value = "select f from Food f where f.itemName=:name and f.restaurantId=:resId")
    Food getFoodByRestaurantName(@Param("name") String name, @Param("resId") Integer resId);

    @Query(value = "select f from Food f where f.restaurantId=:resId")
    ArrayList<Food> getFoodsByRestaurantName(@Param("resId") Integer resId);

}
