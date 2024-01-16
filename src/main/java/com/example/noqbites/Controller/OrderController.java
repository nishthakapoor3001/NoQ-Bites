package com.example.noqbites.Controller;

import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Food;
import com.example.noqbites.DatabaseModels.Order;
import com.example.noqbites.Enums.FoodCategoryEnum;
import com.example.noqbites.Enums.FoodTypeEnum;
import com.example.noqbites.Repositories.CartRepository;
import com.example.noqbites.Repositories.OrderRepository;
import com.example.noqbites.Request.AddToCartDto;
import com.example.noqbites.Response.*;
import com.example.noqbites.Service.AuthenticationService;
import com.example.noqbites.Service.CartService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addToOrders(@RequestParam("token") String token) {

        BaseResponse b = new BaseResponse();

        authenticationService.authenticate(token);

        Customer user = authenticationService.getUser(token);

        List<Cart> cartItems = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        if (cartItems.isEmpty()){
            b.setSuccess(false);
            b.setMessage("Cart is empty");
        }
        else {
            for (int i = 0; i < cartItems.size(); i++) {
                Order order = new Order();
                order.setUser(cartItems.get(i).getUser());
                order.setQuantity(cartItems.get(i).getQuantity());
                order.setProduct(cartItems.get(i).getProduct());
                order.setOrderedDate(new Date());
                orderRepository.save(order);
            }
            b.setMessage("Order is successful");
            b.setSuccess(true);
        }

        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<OrderDto> getOrderItems(@RequestParam("token") String token) {
        OrderDto orderDto = new OrderDto();
        try {
            // authenticate the token
            authenticationService.authenticate(token);

            // find the user
            Customer user = authenticationService.getUser(token);


            List<Order> orderList = orderRepository.findAllOrderByUserOrderByOrderedDateDesc(user);
            if(orderList.isEmpty()){
                orderDto.setMessage("No food is ordered yet by the customer");
                orderDto.setSuccess(false);
                return new ResponseEntity<>(orderDto, HttpStatus.OK);
            }
            List<OrderItemDto> orderItems = new ArrayList<>();
            double totalCost = 0;
            for (Order order : orderList) {
                OrderItemDto orderItemDto = new OrderItemDto(order);
                FoodInfoResponseDto foodInfoResponseDto = new FoodInfoResponseDto();
                foodInfoResponseDto.setFoodId(order.getProduct().getItemId());
                foodInfoResponseDto.setFoodItem(order.getProduct().getItemName());
                foodInfoResponseDto.setFoodCategory(FoodCategoryEnum.valueOf(order.getProduct().getItemCategory()));
                foodInfoResponseDto.setFoodType(FoodTypeEnum.valueOf(order.getProduct().getItemType()));
                foodInfoResponseDto.setPrice(order.getProduct().getPrice());
                orderItemDto.setProduct(foodInfoResponseDto);
                totalCost += orderItemDto.getQuantity() * order.getProduct().getPrice();
                orderItems.add(orderItemDto);
            }
            orderDto.setMessage("Request is processed");
            orderDto.setSuccess(true);
            orderDto.setTotalCost(totalCost);
            orderDto.setOrderItems(orderItems);
        }
        catch (Exception e){
            orderDto.setMessage("An error occurred");
            orderDto.setSuccess(false);
        }
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

}
