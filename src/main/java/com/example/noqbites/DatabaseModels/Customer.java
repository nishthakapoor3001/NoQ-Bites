package com.example.noqbites.DatabaseModels;


import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer customerId;

    @Column(name = "customer_name")
    private @Nonnull String customerName;

    @Column(name = "wat_id")
    private @Nonnull Integer watId;

    @Column(name = "password")
    private @Nonnull String password;

    @Column(name = "email")
    private @Nonnull String email;

    public Customer() {
    }

    public Customer(String customerName, Integer watId, String password, String email) {
        this.customerName = customerName;
        this.watId = watId;
        this.password = password;
        this.email = email;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getWatId() {
        return watId;
    }

    public void setWatId(Integer watId) {
        this.watId = watId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
