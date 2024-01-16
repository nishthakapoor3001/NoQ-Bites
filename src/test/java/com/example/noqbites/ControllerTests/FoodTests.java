package com.example.noqbites.ControllerTests;

import com.example.noqbites.Controller.FoodController;
import com.example.noqbites.DatabaseModels.Food;
import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Repositories.FoodRepository;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.FoodDto;
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
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FoodController.class)
public class FoodTests {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    FoodRepository foodRepository;

    @MockBean
    RestaurantRepository restaurantRepository;

    private Restaurant getRestaurant(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        Restaurant result = mapper.readValue(json, Restaurant.class);

        return result;
    }

    private FoodDto getFood(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        FoodDto result = mapper.readValue(json, FoodDto.class);

        return result;
    }

    private Food getFood2(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        Food result = mapper.readValue(json, Food.class);

        return result;
    }

    @Test
    public void addItemForRestaurant() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFood("", 1, 1, 2)).thenReturn(null);

        String cont = mapper.writeValueAsString(getFood("src/test/java/com/example/noqbites/TestUtil/FoodAddRequest.json"));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/food/add/{pathVariable}", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }

    @Test
    public void addItemForRestaurantNull() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(null);

        Mockito.when(foodRepository.getFood("", 1, 1, 2)).thenReturn(null);

        String cont = mapper.writeValueAsString(getFood("src/test/java/com/example/noqbites/TestUtil/FoodAddRequest.json"));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/food/add/{pathVariable}", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void getFoodByName() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFoodByRestaurantName("Noodles", 4)).thenReturn(getFood2("src/test/java/com/example/noqbites/TestUtil/Food.json"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/food/{restaurantName}/{name}", "Hakka Wok", "Noodles")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }

    @Test
    public void getFoodByNameNullRestaurant() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(null);

        Mockito.when(foodRepository.getFoodByRestaurantName("Hakka Wok", 1)).thenReturn(getFood2("src/test/java/com/example/noqbites/TestUtil/Food.json"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/food/{restaurantName}/{name}", "Hakka Wok", "Noodles")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void getFoodByNameNull() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFoodByRestaurantName("Noodles", 4)).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/food/{restaurantName}/{name}", "Hakka Wok", "Noodles")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void addItemForRestaurantAlreadyPresent() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFood("Latte", 1, 1, 4)).thenReturn(new Food());

        String cont = mapper.writeValueAsString(getFood("src/test/java/com/example/noqbites/TestUtil/FoodAddRequest.json"));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/food/add/{pathVariable}", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void deleteFood() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFoodByRestaurantName("Noodles", 4)).thenReturn(getFood2("src/test/java/com/example/noqbites/TestUtil/Food.json"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/food/{restaurantName}/{name}", "Hakka Wok", "Noodles")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));

    }

    @Test
    public void deleteFoodNullItem() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFoodByRestaurantName("Noodles", 4)).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/food/{restaurantName}/{name}", "Hakka Wok", "Noodles")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void deleteFoodNullRestaurant() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(null);
        Mockito.when(foodRepository.getFoodByRestaurantName("Noodles", 4)).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/food/{restaurantName}/{name}", "Hakka Wok", "Noodles")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void getFoodByRestaurantNullItem() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFoodsByRestaurantName(4)).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/food/{restaurantName}", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void getFoodByRestaurantNullRestaurant() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(null);

        Mockito.when(foodRepository.getFoodsByRestaurantName(4)).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/food/{restaurantName}", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }

    @Test
    public void getFoodByRestaurantEmptyItem() throws Exception {

        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json"));

        Mockito.when(foodRepository.getFoodsByRestaurantName(4)).thenReturn(new ArrayList<>());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/food/{restaurantName}", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));

    }




}
