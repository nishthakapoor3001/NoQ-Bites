package com.example.noqbites.Request;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class CustomerData {
    private @Nonnull int watid;
    private @Nonnull String name;
    private String email;
    private @Nonnull String password;
}
