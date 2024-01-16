package com.example.noqbites.Service;


import com.example.noqbites.DatabaseModels.Cart;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.DatabaseModels.Food;
import com.example.noqbites.Enums.FoodCategoryEnum;
import com.example.noqbites.Enums.FoodTypeEnum;
import com.example.noqbites.Exception.CustomException;
import com.example.noqbites.Repositories.CartRepository;
import com.example.noqbites.Repositories.FoodRepository;
import com.example.noqbites.Request.AddToCartDto;
import com.example.noqbites.Response.CartDto;
import com.example.noqbites.Response.CartItemDto;
import com.example.noqbites.Response.FoodInfoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, Customer user) {

        // validate if the product id is valid
        Optional<Food> optionalProduct  = foodRepository.findById(addToCartDto.getId());

        if (!optionalProduct.isPresent()) {
            throw new CustomException("item id is invalid: " + addToCartDto.getId());
        }

        Optional<Cart> cartItem = Optional.ofNullable(cartRepository.findByProduct(addToCartDto.getId()));

        if(cartItem.isPresent()) {
                cartRepository.updateItemQuantity(addToCartDto.getId(), addToCartDto.getQuantity());

        } else {

            Food product = optionalProduct.get();

            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setUser(user);
            cart.setQuantity(addToCartDto.getQuantity());
            cart.setCreatedDate(new Date());


            // save the cart
            cartRepository.save(cart);

        }


    }

    public CartDto listCartItems(Customer user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList) {
            if(cart.getProduct() == null) continue;
            CartItemDto cartItemDto = new CartItemDto(cart);
            FoodInfoResponseDto foodInfoResponseDto = new FoodInfoResponseDto();
            foodInfoResponseDto.setFoodId(cart.getProduct().getItemId());
            foodInfoResponseDto.setFoodItem(cart.getProduct().getItemName());
            foodInfoResponseDto.setFoodCategory(FoodCategoryEnum.valueOf(cart.getProduct().getItemCategory()));
            foodInfoResponseDto.setFoodType(FoodTypeEnum.valueOf(cart.getProduct().getItemType()));
            foodInfoResponseDto.setPrice(cart.getProduct().getPrice());
            cartItemDto.setProduct(foodInfoResponseDto);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return  cartDto;
    }

    public void deleteCartItem(Integer cartItemId, Customer user) {
        // the item id belongs to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (!optionalCart.isPresent()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw  new CustomException("cart item does not belong to user: " +cartItemId);
        }

        cartRepository.delete(cart);

    }

    public void deleteFullCartForUser(Customer user) {
        // the item id belongs to user

        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        if(cartList == null || cartList.size()<=0) {
            throw new CustomException("No items found in cart for user: "+user.getCustomerId());
        }

        cartRepository.deleteAll(cartList);

    }
}
