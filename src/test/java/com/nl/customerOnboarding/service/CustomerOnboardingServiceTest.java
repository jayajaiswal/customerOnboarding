package com.nl.customerOnboarding.service;

import com.nl.customerOnboarding.domainObject.Account;
import com.nl.customerOnboarding.domainObject.Customer;
import com.nl.customerOnboarding.exception.CustomerNotAddedException;
import com.nl.customerOnboarding.model.AccountDetails;
import com.nl.customerOnboarding.model.CustomerDetails;
import com.nl.customerOnboarding.model.User;
import com.nl.customerOnboarding.util.RandomPasswordGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerOnboardingServiceTest {

    @Mock
    private CustomerOnboardingService customerOnboardingService;
    String password = RandomPasswordGenerator.generatePassword();

    @Test
    public void registerNewCustomer_successful_test() {
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
        when(customerOnboardingService.registerCustomer(customer)).thenReturn(customerDetails);

    }

    @Test
    public void registerNewCustomer_Unsuccessful_countryNa_test() {
        Map<String, String> credentials = new HashMap<>();
        Date date = new Date(1994, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "Belgium",
                null);
        when(customerOnboardingService.registerCustomer(customer)).thenThrow(CustomerNotAddedException.class);

    }

    @Test
    public void registerNewCustomer_Unsuccessful_age_test() {
        Map<String, String> credentials = new HashMap<>();
        Date date = new Date(2022, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "India",
                null);
        when(customerOnboardingService.registerCustomer(customer)).thenThrow(CustomerNotAddedException.class);

    }

    @Test
    public void loginSuccessfulTest() {
        User user = new User("john.doe@gmail.com", password);
        when(customerOnboardingService.loginCustomer(user)).thenReturn(true);
    }

    @Test
    public void loginUnSuccessfulTest() {
        User user = new User("john@gmail.com", password);
        when(customerOnboardingService.loginCustomer(user)).thenReturn(false);
    }

    @Test
    public void getOverviewTest() {
        long accountNumber = 1001;
        AccountDetails accountDetails = new AccountDetails(
                1001,
                "Savings",
                500,
                "$",
                new Date(1994, 2, 10)
        );
        when(customerOnboardingService.getOverview(accountNumber)).thenReturn(accountDetails);
    }

}
