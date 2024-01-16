package com.example.noqbites.ControllerTests;


import com.example.noqbites.Controller.LoginPageController;
import com.example.noqbites.Controller.SignupPageController;
import com.example.noqbites.DatabaseModels.AuthenticationToken;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Enums.LoginTypeEnum;
import com.example.noqbites.Repositories.CustomerRepository;
import com.example.noqbites.Repositories.FoodRepository;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.CustomerData;
import com.example.noqbites.Request.CustomerLoginDto;
import com.example.noqbites.Request.RestaurantDto;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Response.LoginDto;
import com.example.noqbites.Service.AuthenticationService;
import com.example.noqbites.Service.LoginPageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(LoginPageController.class)
public class LoginControllerTests {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;


    @MockBean
    LoginPageService loginPageService;

    @Test
    public void LoginWithWrongCustomer() throws Exception {
       CustomerLoginDto customerLoginDto = new CustomerLoginDto();
       customerLoginDto.setName("test");
       customerLoginDto.setPassword("testing");
       customerLoginDto.setWatId(123456);
       customerLoginDto.setType(LoginTypeEnum.USER);

       LoginDto b = new LoginDto();
       b.setSuccess(true);

        Mockito.when(loginPageService.login(customerLoginDto)).thenReturn(b);


        String cont = mapper.writeValueAsString(customerLoginDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk());

    }

    @Test
    public void LoginWithWrongRestaurant() throws Exception {
        CustomerLoginDto customerLoginDto = new CustomerLoginDto();
        customerLoginDto.setName("test");
        customerLoginDto.setPassword("testing");
        customerLoginDto.setWatId(123456);
        customerLoginDto.setType(LoginTypeEnum.RESTAURANT);

        LoginDto b = new LoginDto();
        b.setSuccess(true);

        Mockito.when(loginPageService.login(customerLoginDto)).thenReturn(b);

        String cont = mapper.writeValueAsString(customerLoginDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/login/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk());

    }





}

