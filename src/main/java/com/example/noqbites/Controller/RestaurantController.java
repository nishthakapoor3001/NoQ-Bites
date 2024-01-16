package com.example.noqbites.Controller;

import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.CustomerData;
import com.example.noqbites.Request.RestaurantDto;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Response.RestaurantInfoResponse;
import com.example.noqbites.Response.RestaurantsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/getAll")
    public ResponseEntity<RestaurantsResponse> getAllRestaurants() {
        RestaurantsResponse b = new RestaurantsResponse();
        try {
            List<Restaurant> allRestaurants = restaurantRepository.getAllRestaurants(Sort.by("restaurantName"));
            if (allRestaurants == null || allRestaurants.isEmpty()) {
                b.setSuccess(false);
                b.setMessage("No restaurants found");
            } else {
                List<RestaurantInfoResponse> restaurants = new ArrayList<>();

                restaurants = allRestaurants.stream().filter(r -> r.getRestaurantName() != null && !r.getRestaurantName().isEmpty())
                        .map(r -> new RestaurantInfoResponse(r.getRestaurantName(), r.getAddress())).collect(Collectors.toList());
                b.setRestaurants(restaurants);
                b.setSuccess(true);
                b.setMessage("Request has been processed");
            }
        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("There is an error processing this request");
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<RestaurantsResponse> entity = new ResponseEntity<>(b, headers, HttpStatus.OK);

        return entity;
    }

    @GetMapping("/getByName")
    public ResponseEntity<RestaurantInfoResponse> getAllRestaurantByName(@RequestParam String restaurantName) {
        RestaurantInfoResponse b = new RestaurantInfoResponse();
        try {
            Restaurant res = restaurantRepository.getRestaurantByName(restaurantName);
            if (res == null) {
                b.setSuccess(false);
                b.setMessage("Restaurant not found");
            } else {
                b.setRestaurantName(res.getRestaurantName());
                b.setAddress(res.getAddress());
                b.setSuccess(true);
                b.setMessage("Request has been processed");
            }
        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("There is an error processing this request");
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<RestaurantInfoResponse> entity = new ResponseEntity<>(b, headers, HttpStatus.OK);

        return entity;
    }

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        BaseResponse b = new BaseResponse();
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantName(restaurantDto.getRestaurantName());
            restaurant.setAddress(restaurantDto.getAddress());
            restaurant.setPassword(restaurantDto.getPassword());
            restaurantRepository.save(restaurant);
            b.setMessage("Restaurant has been registered");
            b.setSuccess(true);

        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("There is an error processing this request");
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<BaseResponse> entity = new ResponseEntity<>(b, headers, HttpStatus.OK);

        return entity;
    }
}
