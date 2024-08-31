package com.example.noqbites.Repositories;

import com.example.noqbites.DatabaseModels.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer > {
    @Query(value = "SELECT u FROM Restaurant u where u.restaurantName=:x and u.password=:y")
    Restaurant getRestaurantByNameAndPassword(@Param("x") String name, @Param("y") String password);

    @Query(value = "SELECT r  FROM Restaurant r")
    List<Restaurant> getAllRestaurants(Sort sort);

    @Query(value = "SELECT u FROM Restaurant u where u.restaurantName=:x")
    Restaurant getRestaurantByName(@Param("x") String name);

    @Query(value = "Select r from Restaurant r where r.id=:x")
    Restaurant getRestaurantById(@Param("x") Integer id);

}
