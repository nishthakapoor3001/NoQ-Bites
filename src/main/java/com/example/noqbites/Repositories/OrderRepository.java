package com.example.noqbites.Repositories;

import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllOrderByUserOrderByOrderedDateDesc(Customer user);

}
