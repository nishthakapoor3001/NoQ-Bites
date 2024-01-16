package com.example.noqbites.ControllerTests;

import com.example.noqbites.Controller.SignupPageController;
import com.example.noqbites.DatabaseModels.AuthenticationToken;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.Repositories.CustomerRepository;
import com.example.noqbites.Request.CustomerData;
import com.example.noqbites.Service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignupPageController.class)
public class SignUpTests {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private AuthenticationService authenticationService;

    private CustomerData createCustomer(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        CustomerData result = mapper.readValue(json, CustomerData.class);

        return result;
    }

    private Customer createCustomerForRepo(CustomerData customer) {
        return new Customer(customer.getName(), customer.getWatid(), customer.getPassword(), customer.getEmail());
    }

    @Test
    public void signUpWithCorrectCustomer() throws Exception {
        CustomerData customer = createCustomer("src/test/java/com/example/noqbites/TestUtil/CustomerData.json");
        Customer user = createCustomerForRepo(customer);
        AuthenticationToken a = new AuthenticationToken();
        Mockito.when(customerRepository.findByWatId(user.getWatId())).thenReturn(null);
        Mockito.when(customerRepository.save(user)).thenReturn(user);

        String cont = mapper.writeValueAsString(customer);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));
    }

    @Test
    public void signUpWithAlreadyExistingCustomer() throws Exception {
        CustomerData customer = createCustomer("src/test/java/com/example/noqbites/TestUtil/CustomerData.json");
        Customer user = createCustomerForRepo(customer);
        Mockito.when(customerRepository.findByWatId(user.getWatId())).thenReturn(user);
        Mockito.when(customerRepository.save(user)).thenReturn(user);

        String cont = mapper.writeValueAsString(customer);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));
    }
}
