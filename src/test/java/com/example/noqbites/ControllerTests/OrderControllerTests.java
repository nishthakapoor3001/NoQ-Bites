package com.example.noqbites.ControllerTests;

import com.example.noqbites.Controller.OrderController;
import com.example.noqbites.Controller.SignupPageController;
import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Food;
import com.example.noqbites.DatabaseModels.Order;
import com.example.noqbites.Repositories.CartRepository;
import com.example.noqbites.Repositories.OrderRepository;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void addToOrders() throws Exception {
        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        Cart p = new Cart();
        p.setProduct(new Food());
        p.setQuantity(1);
        p.setUser(c);
        List<Cart> cartItems = new ArrayList<>();
        cartItems.add(p);

        Mockito.when(cartRepository.findAllByUserOrderByCreatedDateDesc(c)).thenReturn(cartItems);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order/add")
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }

    @Test
    public void addToOrdersEmptyList() throws Exception {
        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        Mockito.when(cartRepository.findAllByUserOrderByCreatedDateDesc(c)).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/order/add")
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void getOrderItems() throws Exception {
        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        Order o = new Order();
        Food f = new Food();
        f.setItemId(1);
        f.setItemCategory(1);
        f.setPrice(10F);
        f.setItemType(1);
        o.setId(1);
        o.setProduct(f);
        o.setQuantity(1);
        List<Order> orders = new ArrayList<>();
        orders.add(o);

        Mockito.when(orderRepository.findAllOrderByUserOrderByOrderedDateDesc(c)).thenReturn(orders);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/order/")
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }

    @Test
    public void getOrderItemsEmpty() throws Exception {
        Customer c  = new Customer("test", 21020061, "testing", "testing@gmail.com");

        Mockito.when(authenticationService.getUser("55f4d438-0c4e-409f-b16e-b7d7127e6f7d")).thenReturn(c);

        Mockito.when(orderRepository.findAllOrderByUserOrderByOrderedDateDesc(c)).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/order/")
                .param("token", "55f4d438-0c4e-409f-b16e-b7d7127e6f7d")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }
}
