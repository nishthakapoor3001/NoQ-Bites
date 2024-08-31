package com.example.noqbites.Request;

import com.example.noqbites.Enums.LoginTypeEnum;
import jakarta.validation.constraints.NotNull;

public class CustomerLoginDto {
    private Integer watId;

    private String name;

    @NotNull(message = "password should not be null")
    private String password;

    @NotNull
    private LoginTypeEnum type;

    public Integer getWatId() {
        return watId;
    }

    public void setWatId(Integer watId) {
        this.watId = watId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginTypeEnum getType() {
        return type;
    }

    public void setType(LoginTypeEnum type) {
        this.type = type;
    }
}
