package com.example.noqbites.Repositories;

import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Food;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(Customer user);

    @Query("SELECT c FROM Cart c where c.product.itemId=:i")
    Cart findByProduct(@Param("i") int itemID);

    @Modifying
    @Transactional
    @Query("update Cart c set c.quantity=:q where c.product.itemId=:i")
    int updateItemQuantity(@Param("i") int itemId, @Param("q") int quantity);

}
