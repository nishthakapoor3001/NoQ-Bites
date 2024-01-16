package com.example.noqbites.Controller;

import com.example.noqbites.Request.CustomerLoginDto;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Service.LoginPageService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginPageController {
//    private CustomerRepositoryCustomImpl customerRepository = new CustomerRepositoryCustomImpl();

    @Autowired
    LoginPageService loginPageService;

    @Autowired
    private MeterRegistry meterRegistry;

    @PostMapping
    @Timed(value = "users.login", description = "Time taken for user login")
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody CustomerLoginDto loginDto){
        BaseResponse b = loginPageService.login(loginDto);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<BaseResponse> entity = new ResponseEntity<>(b,headers, HttpStatus.OK);

        return entity;
    }

    @PostMapping("/restaurant")
    public ResponseEntity<BaseResponse> loginAsRestaurant(@Valid @RequestBody CustomerLoginDto loginDto){
        BaseResponse b = loginPageService.login(loginDto);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<BaseResponse> entity = new ResponseEntity<>(b,headers, HttpStatus.OK);

        return entity;
    }


}
