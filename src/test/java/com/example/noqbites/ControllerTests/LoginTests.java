package com.example.noqbites.ControllerTests;

import com.example.noqbites.Controller.LoginPageController;
import com.example.noqbites.Controller.SignupPageController;
import com.example.noqbites.DatabaseModels.AuthenticationToken;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Repositories.CustomerRepository;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.CustomerData;
import com.example.noqbites.Request.CustomerLoginDto;
import com.example.noqbites.Request.RestaurantDto;
import com.example.noqbites.Response.BaseResponse;
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
@WebMvcTest(LoginPageService.class)
public class LoginTests {

    @Autowired
    @InjectMocks
    private LoginPageService loginPageService;
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private AuthenticationService authenticationService;

    @Before
    public void initialize(){
        this.loginPageService = new LoginPageService();
    }
    private CustomerLoginDto LoginCustomer(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        CustomerLoginDto result = mapper.readValue(json, CustomerLoginDto.class);

        return result;
    }

    private CustomerLoginDto LoginRestaurant(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        CustomerLoginDto result = mapper.readValue(json, CustomerLoginDto.class);

        return result;
    }

    private Customer createCustomerForRepo(CustomerData customer) {
        return new Customer(customer.getName(), customer.getWatid(), customer.getPassword(), customer.getEmail());
    }

    private CustomerData createCustomer(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        CustomerData result = mapper.readValue(json, CustomerData.class);

        return result;
    }

    private Restaurant createRestaurant(String name, String address, String password) {
        return new Restaurant(name, address, password);
    }

    @Test
    public void LoginWithCorrectCustomer() throws Exception {
        CustomerLoginDto customer = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginData.json");

        CustomerLoginDto res = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginDataForRestaurant.json");

        CustomerData customerFromDb = createCustomer("src/test/java/com/example/noqbites/TestUtil/CustomerData.json");

        Customer user = createCustomerForRepo(customerFromDb);

        Restaurant restaurantFromDb = createRestaurant("test", "password", "address");

        AuthenticationToken a = new AuthenticationToken();
        Mockito.when(authenticationService.getToken(user)).thenReturn(a);

        Mockito.when(customerRepository.getCustomerByWatId(customer.getWatId(), customer.getPassword())).thenReturn(user);
        Mockito.when(restaurantRepository.getRestaurantByNameAndPassword(res.getName().toLowerCase(), res.getPassword())).thenReturn(restaurantFromDb);

        BaseResponse b = loginPageService.login(customer);

        assertEquals("it got passed", Boolean.TRUE, b.getSuccess());

    }

    @Test
    public void LoginWithWrongCustomer() throws Exception {
        CustomerLoginDto customer = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginData.json");

        CustomerLoginDto res = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginDataForRestaurant.json");

        CustomerData customerFromDb = createCustomer("src/test/java/com/example/noqbites/TestUtil/CustomerData.json");

        Customer user = createCustomerForRepo(customerFromDb);

        Restaurant restaurantFromDb = createRestaurant("test", "password", "address");

        Mockito.when(customerRepository.getCustomerByWatId(customer.getWatId(), customer.getPassword())).thenReturn(null);
        Mockito.when(restaurantRepository.getRestaurantByNameAndPassword(res.getName().toLowerCase(), res.getPassword())).thenReturn(restaurantFromDb);

        BaseResponse b = loginPageService.login(customer);

        assertEquals("it got failed", Boolean.FALSE, b.getSuccess());

    }


    @Test
    public void LoginWithCorretRestaurant() throws Exception {
        CustomerLoginDto customer = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginData.json");

        CustomerLoginDto res = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginDataForRestaurant.json");

        CustomerData customerFromDb = createCustomer("src/test/java/com/example/noqbites/TestUtil/CustomerData.json");

        Customer user = createCustomerForRepo(customerFromDb);

        Restaurant restaurantFromDb = createRestaurant("test", "password", "address");

        Mockito.when(customerRepository.getCustomerByWatId(customer.getWatId(), customer.getPassword())).thenReturn(user);
        Mockito.when(restaurantRepository.getRestaurantByNameAndPassword(res.getName().toLowerCase(), res.getPassword())).thenReturn(restaurantFromDb);

        BaseResponse b = loginPageService.login(res);

        assertEquals("it got passed", Boolean.TRUE, b.getSuccess());

    }

    @Test
    public void LoginWithWrongRestaurant() throws Exception {
        CustomerLoginDto customer = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginData.json");

        CustomerLoginDto res = LoginCustomer("src/test/java/com/example/noqbites/TestUtil/LoginDataForRestaurant.json");

        CustomerData customerFromDb = createCustomer("src/test/java/com/example/noqbites/TestUtil/CustomerData.json");

        Customer user = createCustomerForRepo(customerFromDb);

        Restaurant restaurantFromDb = createRestaurant("test", "password", "address");

        Mockito.when(customerRepository.getCustomerByWatId(customer.getWatId(), customer.getPassword())).thenReturn(user);
        Mockito.when(restaurantRepository.getRestaurantByNameAndPassword(res.getName().toLowerCase(), res.getPassword())).thenReturn(null);

        BaseResponse b = loginPageService.login(res);

        assertEquals("it got failed", Boolean.FALSE, b.getSuccess());

    }

}
