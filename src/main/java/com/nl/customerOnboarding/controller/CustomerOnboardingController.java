package com.nl.customerOnboarding.controller;

import com.nl.customerOnboarding.exception.AccountNotFoundException;
import com.nl.customerOnboarding.exception.CustomerNotAddedException;
import com.nl.customerOnboarding.model.CustomerDetails;
import com.nl.customerOnboarding.model.User;
import com.nl.customerOnboarding.service.CustomerOnboardingService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/loyalbank", produces = "application/json")
public class CustomerOnboardingController {

    @Autowired
    CustomerOnboardingService customerOnboardingService;

    @PostMapping(value = "/registerCustomer")
    public ResponseEntity registerCustomer(@RequestBody CustomerDetails customer) {

        try {
            Map<String, String> loginCredentials = customerOnboardingService.registerCustomer(customer);
            return new ResponseEntity<>("Customer created: " + loginCredentials, HttpStatus.OK);
        } catch (CustomerNotAddedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/login")
    public ResponseEntity initiateLogin(@RequestBody User user) {

        if (customerOnboardingService.loginCustomer(user) == true) {
            return new ResponseEntity("User logged in", HttpStatus.OK);
        } else {
            return new ResponseEntity("Username or password incorrect", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/overview")
    public ResponseEntity getAccountOverview(@PathParam(value = "accountNumber") long accountNumber) {

        try {
            return new ResponseEntity(customerOnboardingService.getOverview(accountNumber), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
