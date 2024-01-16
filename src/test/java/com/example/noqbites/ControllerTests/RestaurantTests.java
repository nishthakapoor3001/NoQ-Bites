package com.example.noqbites.ControllerTests;

import com.example.noqbites.Controller.RestaurantController;

import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.RestaurantDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RestaurantController.class)
public class RestaurantTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantRepository restaurantRepository;

    private List<Restaurant> getRestaurants(String filename) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filename)));

        List<Restaurant> list = mapper.readValue(json, new TypeReference<List<Restaurant>>(){});

        return list;

    }

    private String getFileContentAsString(String filePath) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return json;
    }

    private Restaurant getRestaurant(String filename) throws IOException {

        String json = new String(Files.readAllBytes(Paths.get(filename)));
        Restaurant result = mapper.readValue(json, Restaurant.class);

        return result;
    }

    @Test
    public void getAllRestaurants() throws Exception {
        List<Restaurant> allRestaurants = getRestaurants("src/test/java/com/example/noqbites/TestUtil/AllRestaurants.json");
        Mockito.when(restaurantRepository.getAllRestaurants(Sort.by("restaurantName"))).thenReturn(allRestaurants);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/restaurant/getAll")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(content().json(getFileContentAsString("src/test/java/com/example/noqbites/TestUtil/RestaurantAllExpected.json")));
    }

    @Test
    public void getAllRestaurantsNull() throws Exception {
        List<Restaurant> allRestaurants = getRestaurants("src/test/java/com/example/noqbites/TestUtil/AllRestaurants.json");
        Mockito.when(restaurantRepository.getAllRestaurants(Sort.by("restaurantName"))).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/restaurant/getAll")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));
    }

    @Test
    public void getRestaurantByName() throws Exception {
        Restaurant restaurant = getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json");
        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(restaurant);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/restaurant/getByName")
                .param("restaurantName", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"))
                .andExpect(content().json(getFileContentAsString("src/test/java/com/example/noqbites/TestUtil/RestaurantExpected.json")));
    }

    @Test
    public void getRestaurantByNameNullCase() throws Exception {
        Restaurant restaurant = getRestaurant("src/test/java/com/example/noqbites/TestUtil/Restaurant.json");
        Mockito.when(restaurantRepository.getRestaurantByName("Hakka Wok")).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/restaurant/getByName")
                .param("restaurantName", "Hakka Wok")
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("false"));
    }

    @Test
    public void addRestaurant() throws Exception {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setRestaurantName("test");
        restaurantDto.setPassword("testing");
        restaurantDto.setAddress("xyz");

        String cont = mapper.writeValueAsString(restaurantDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/restaurant/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(cont);

        mvc.perform(mockRequest).andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value("true"));
    }


}
