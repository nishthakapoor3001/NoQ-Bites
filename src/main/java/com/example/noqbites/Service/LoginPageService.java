package com.example.noqbites.Service;

import com.example.noqbites.DatabaseModels.AuthenticationToken;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Enums.LoginTypeEnum;
import com.example.noqbites.Exception.CustomException;
import com.example.noqbites.Repositories.CustomerRepository;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.CustomerLoginDto;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Response.LoginDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Transactional
public class LoginPageService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    AuthenticationService authenticationService;

    public LoginDto login(CustomerLoginDto loginDto) {
        LoginDto b = new LoginDto();
        try {
            if (loginDto.getType().equals(LoginTypeEnum.USER)){
                if(loginDto.getName()!=null){
                    b.setSuccess(false);
                    b.setMessage("name is not allowed to enter");
                    return b;
                }
                Customer user = customerRepository.getCustomerByWatId(loginDto.getWatId(), loginDto.getPassword());
                if (user ==null){
                    b.setSuccess(false);
                    b.setMessage("User not found");
                }
                else {
                    AuthenticationToken token = authenticationService.getToken(user);
                    if (Objects.isNull(token)) {
                        throw new CustomException("token is not present");
                    }
                    b.setToken(token.getToken());
                    b.setSuccess(true);
                    b.setMessage("User logged in successfully");
                }
            }
            else if (loginDto.getType().equals(LoginTypeEnum.RESTAURANT)){
                if(loginDto.getWatId()!=null){
                    b.setSuccess(false);
                    b.setMessage("watId is not allowed to enter");
                    return b;
                }
                Restaurant res = restaurantRepository.getRestaurantByNameAndPassword(loginDto.getName().toLowerCase(), loginDto.getPassword());
                if (res ==null){
                    b.setSuccess(false);
                    b.setMessage("User not found");
                }
                else{
                    b.setSuccess(true);
                    b.setMessage("User logged in successfully");
                }
            }
            else {
                b.setSuccess(false);
                b.setMessage("User not found");
            }
        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("There is an error processing this request");
        }
        return b;
    }
}
