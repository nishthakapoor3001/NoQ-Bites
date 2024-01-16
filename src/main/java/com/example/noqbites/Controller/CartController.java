package com.example.noqbites.Controller;

import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.Request.AddToCartDto;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Response.CartDto;
import com.example.noqbites.Service.AuthenticationService;
import com.example.noqbites.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;


    // post cart api
    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                  @RequestParam("token") String token) {

        BaseResponse b = new BaseResponse();
        // authenticate the token
        authenticationService.authenticate(token);

        Customer user = authenticationService.getUser(token);


        cartService.addToCart(addToCartDto, user);

        b.setMessage("Item added to cart");
        b.setSuccess(true);

        return new ResponseEntity<>(b, HttpStatus.OK);
    }


    // get all cart items for a user
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
        // authenticate the token
        authenticationService.authenticate(token);

        // find the user
        Customer user = authenticationService.getUser(token);

        // get cart items

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete a cart item for a user

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<BaseResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                       @RequestParam("token") String token) {

        BaseResponse b = new BaseResponse();

        // authenticate the token
        authenticationService.authenticate(token);
        Customer user = authenticationService.getUser(token);

        b.setMessage("Item deleted from cart");
        b.setSuccess(true);

        cartService.deleteCartItem(itemId, user);

        return new ResponseEntity<>(b, HttpStatus.OK);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> deleteCartUser(@RequestParam("token") String token) {

        BaseResponse b = new BaseResponse();

        // authenticate the token
        authenticationService.authenticate(token);
        Customer user = authenticationService.getUser(token);


        cartService.deleteFullCartForUser(user);


        b.setMessage("All items are deleted from cart");
        b.setSuccess(true);

        return new ResponseEntity<>(b, HttpStatus.OK);

    }

}
