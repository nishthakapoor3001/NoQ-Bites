package com.example.noqbites.Controller;

import com.example.noqbites.DatabaseModels.Food;
import com.example.noqbites.DatabaseModels.Restaurant;
import com.example.noqbites.Enums.FoodCategoryEnum;
import com.example.noqbites.Enums.FoodTypeEnum;
import com.example.noqbites.Repositories.FoodRepository;
import com.example.noqbites.Repositories.RestaurantRepository;
import com.example.noqbites.Request.FoodDto;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Response.FoodInfoResponseDto;
import com.example.noqbites.Response.FoodsByRestaurantResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin("*")
@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @PostMapping("/add/{restaurantName}")
    public ResponseEntity<BaseResponse> addFood(@PathVariable("restaurantName") String restaurantName, @RequestBody @Valid FoodDto foodDto){
        BaseResponse b = new BaseResponse();
        try{
            Restaurant res = restaurantRepository.getRestaurantByName(restaurantName);
            if (res == null) {
                b.setMessage("Restaurant does not exist");
                b.setSuccess(false);
            }
            else{
                Food f = new Food();
                Food food = foodRepository.getFood(foodDto.getItemName(), foodDto.getItemCategory().getValue(), foodDto.getItemType().getValue(), res.getRestaurantId());
                if (food != null) {
                    b.setSuccess(false);
                    b.setMessage("Duplicate food item already exists");

                } else {
                    f.setItemName(foodDto.getItemName());
                    f.setItemType(foodDto.getItemType().getValue());
                    f.setItemCategory(foodDto.getItemCategory().getValue());
                    f.setPrice(foodDto.getPrice());
                    f.setRestaurantId(res.getRestaurantId());
                    foodRepository.save(f);
                    b.setMessage("Food item is added");
                    b.setSuccess(true);
                }
            }
        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("An error has occurred");
        }
        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<BaseResponse> entity = new ResponseEntity<>(b, headers, HttpStatus.OK);

        return entity;
    }

    @GetMapping("/{restaurantName}/{name}")
    public ResponseEntity<FoodInfoResponseDto> getfoodByName(@PathVariable("name") String name, @PathVariable("restaurantName") String restaurantName) {
        FoodInfoResponseDto b = new FoodInfoResponseDto();
        try {
            Restaurant res = restaurantRepository.getRestaurantByName(restaurantName);
            if (res == null){
                b.setSuccess(false);
                b.setMessage("Restaurant not found");
            }
            else {
                Food food = foodRepository.getFoodByRestaurantName(name, res.getRestaurantId());
                if (food == null) {
                    b.setSuccess(false);
                    b.setMessage("Food item not found");
                } else {
                    b.setFoodItem(food.getItemName());
                    b.setFoodCategory(FoodCategoryEnum.valueOf(food.getItemCategory()));
                    b.setFoodType(FoodTypeEnum.valueOf(food.getItemType()));
                    b.setPrice(food.getPrice());
                    b.setSuccess(true);
                    b.setMessage("Request has been processed");
                }
            }
        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("There is an error processing this request");
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<FoodInfoResponseDto> entity = new ResponseEntity<>(b, headers, HttpStatus.OK);

        return entity;
    }

    @DeleteMapping("/{restaurantName}/{name}")
    public ResponseEntity<BaseResponse> deleteFood(@PathVariable("name") String name, @PathVariable("restaurantName") String restaurantName){
        BaseResponse b = new BaseResponse();
        try {
            Restaurant res = restaurantRepository.getRestaurantByName(restaurantName);
            if (res == null) {
                b.setSuccess(false);
                b.setMessage("Restaurant not found");
            } else {
                Food food = foodRepository.getFoodByRestaurantName(name, res.getRestaurantId());
                if (food == null) {
                    b.setSuccess(false);
                    b.setMessage("Food item not found");
                } else {
                    foodRepository.delete(food);
                    b.setSuccess(true);
                    b.setMessage("Food item deleted successfully");
                }
            }
        }
        catch (Exception e){
            b.setMessage("Some error occurred");
            b.setSuccess(false);
        }

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<BaseResponse> entity = new ResponseEntity<>(b, headers, HttpStatus.OK);
        return entity;

    }

    @GetMapping("/{restaurantName}")
    public ResponseEntity<FoodsByRestaurantResponseDto> getFoodByRestaurant(@PathVariable("restaurantName") String restaurantName){
        FoodsByRestaurantResponseDto foods = new FoodsByRestaurantResponseDto();
        try {
            Restaurant res = restaurantRepository.getRestaurantByName(restaurantName);
            if (res == null) {
                foods.setSuccess(false);
                foods.setMessage("Restaurant not found");
            } else {
                ArrayList<Food> food = foodRepository.getFoodsByRestaurantName(res.getRestaurantId());
                if (food.isEmpty()){
                    foods.setSuccess(false);
                    foods.setMessage("No food items found");
                }
                else {
                    ArrayList<FoodInfoResponseDto> f = new ArrayList<>();
                    for (int i=0;i<food.size();i++){
                        FoodInfoResponseDto foodInfoResponseDto = new FoodInfoResponseDto();
                        foodInfoResponseDto.setFoodId(food.get(i).getItemId());
                        foodInfoResponseDto.setFoodItem(food.get(i).getItemName());
                        foodInfoResponseDto.setFoodCategory(FoodCategoryEnum.valueOf(food.get(i).getItemCategory()));
                        foodInfoResponseDto.setFoodType(FoodTypeEnum.valueOf(food.get(i).getItemType()));
                        foodInfoResponseDto.setPrice(food.get(i).getPrice());
                        foodInfoResponseDto.setSuccess(null);
                        foodInfoResponseDto.setMessage(null);
                        f.add(foodInfoResponseDto);
                    }
                    foods.setMessage("Request is processed");
                    foods.setSuccess(true);
                    foods.setFood(f);
                }
            }
        }
        catch (Exception e){
            foods.setMessage("An error occurred");
            foods.setSuccess(false);
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<FoodsByRestaurantResponseDto> entity = new ResponseEntity<>(foods, headers, HttpStatus.OK);
        return entity;
    }

}
