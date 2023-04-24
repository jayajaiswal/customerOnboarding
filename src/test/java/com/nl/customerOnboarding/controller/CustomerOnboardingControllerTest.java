package com.nl.customerOnboarding.controller;

import com.nl.customerOnboarding.domainObject.Account;
import com.nl.customerOnboarding.domainObject.Customer;
import com.nl.customerOnboarding.exception.CustomerNotAddedException;
import com.nl.customerOnboarding.model.AccountDetails;
import com.nl.customerOnboarding.model.CustomerDetails;
import com.nl.customerOnboarding.model.User;
import com.nl.customerOnboarding.repository.CustomerOnboardingRepository;
import com.nl.customerOnboarding.service.CustomerOnboardingService;
import com.nl.customerOnboarding.util.RandomPasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerOnboardingControllerTest {

    @Mock
    CustomerOnboardingService service;
    @InjectMocks
    CustomerOnboardingController controller;
    @Mock
    CustomerOnboardingRepository repo;

    String password = RandomPasswordGenerator.generatePassword();

    @Test
    public void registerCustomerTest_successful() {

        Map<String, String> credentials = new HashMap<>();
        Date date = new Date(1994, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "USA",
                null);
        CustomerDetails customerDetails = new CustomerDetails(
                customer.getName(),
                customer.getEmail(),
                customer.getDateOfBirth(),
                customer.getCountry(),
                new Account(
                        1002,
                        "Savings", 1000,
                        "$",
                        new Date(2000, 01, 7)),
                password
        );
        when(service.registerCustomer(customer)).thenReturn(customerDetails);
        ResponseEntity response = controller.registerCustomer(customer);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void registerCustomerTest_unsuccessful_countryNA() {

        Map<String, String> credentials = new HashMap<>();
        Date date = new Date(1994, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "Belgium",
                null);

        when(service.registerCustomer(customer)).thenThrow(CustomerNotAddedException.class);
        ResponseEntity response = controller.registerCustomer(customer);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void registerCustomerTest_unsuccessful_invalidAge() {

        Map<String, String> credentials = new HashMap<>();
        Date date = new Date(1994, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "USA",
                null);
        when(service.registerCustomer(customer)).thenThrow(CustomerNotAddedException.class);
        ResponseEntity response = controller.registerCustomer(customer);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }

    @Test
    public void login_successful() {

        User user = new User("john.doe@gmail.com", password);
        when(service.loginCustomer(user)).thenReturn(true);
        ResponseEntity response = controller.initiateLogin(user);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void login_unSuccessful() {

        User user = new User("john@gmail.com", "abc");
        when(service.loginCustomer(user)).thenReturn(false);
        ResponseEntity response = controller.initiateLogin(user);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getAccountOverviewTest() {

        long accountNumber = 1001;
        AccountDetails accountDetails = new AccountDetails(
                1001,
                "Savings",
                500,
                "$",
                new Date(1994, 2, 10)
        );
        when(service.getOverview(accountNumber)).thenReturn(accountDetails);
        ResponseEntity response = controller.getAccountOverview(accountNumber);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
