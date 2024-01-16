package com.example.noqbites.ControllerTests;

import com.example.noqbites.Controller.CartController;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.Request.AddToCartDto;
import com.example.noqbites.Response.CartDto;
import com.example.noqbites.Service.AuthenticationService;
import com.example.noqbites.Service.CartService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
public class CartTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private AuthenticationService authenticationService;

    private String getFileContentAsString(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return json;
    }

    private CartDto getCartDto(String filename) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        CartDto result = mapper.readValue(json, CartDto.class);
        return result;
    }

    @Test
    public void getItemsFromCart() throws Exception {
        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        Mockito.when(cartService.listCartItems(c)).thenReturn(getCartDto("src/test/java/com/example/noqbites/TestUtil/Cart.json"));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/cart/")
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(content().json(getFileContentAsString("src/test/java/com/example/noqbites/TestUtil/Cart.json")));


    }

    @Test
    public void addToCart() throws Exception {
        AddToCartDto addToCartDto = new AddToCartDto();
        addToCartDto.setId(1);
        addToCartDto.setQuantity(1);

        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        String cont = mapper.writeValueAsString(addToCartDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));
    }

    @Test
    public void deleteCartItem() throws Exception {

        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/cart/delete/{cartItemId}", 1)
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }

    @Test
    public void deleteCartItemUser() throws Exception {

        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/cart/delete")
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }
}
