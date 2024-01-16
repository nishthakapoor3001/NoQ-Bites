package com.example.noqbites.Repositories;

import com.example.noqbites.DatabaseModels.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer > {
    Customer findByEmail(String email);

    @Query("SELECT u FROM Customer u where u.watId=:x")
    Customer findByWatId(@Param("x") Integer watId);

    @Query("SELECT u FROM Customer u where u.watId=:x and u.password=:y")
    Customer getCustomerByWatId(@Param("x") Integer watId, @Param("y") String password);
}
