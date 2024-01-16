package com.example.noqbites.Controller;

import com.example.noqbites.DatabaseModels.AuthenticationToken;
import com.example.noqbites.DatabaseModels.Customer;
import com.example.noqbites.Repositories.CustomerRepository;
import com.example.noqbites.Request.CustomerData;
import com.example.noqbites.Response.BaseResponse;
import com.example.noqbites.Service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class SignupPageController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> addCustomer(@RequestBody @Valid CustomerData customerData){
        BaseResponse b = new BaseResponse();
        try {
            Customer user = customerRepository.findByWatId(customerData.getWatid());
            if(user != null) {
                b.setSuccess(false);
                b.setMessage("Customer with this Wat Id already exists");
            } else {
                user = new Customer();
                user.setWatId(customerData.getWatid());
                user.setCustomerName(customerData.getName());
                user.setPassword(customerData.getPassword());
                user.setEmail(customerData.getEmail());
                customerRepository.save(user);

                final AuthenticationToken authenticationToken = new AuthenticationToken(user);

                authenticationService.saveConfirmationToken(authenticationToken);

                b.setMessage("Customer has been registered");
                b.setSuccess(true);
            }

        } catch (Exception e) {
            b.setSuccess(false);
            b.setMessage("There is an error processing this request");
        }

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<BaseResponse> entity = new ResponseEntity<>(b,headers, HttpStatus.OK);

        return entity;
    }
}
