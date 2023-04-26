package com.nl.customerOnboarding.service;

import com.nl.customerOnboarding.domainObject.Account;
import com.nl.customerOnboarding.domainObject.Customer;
import com.nl.customerOnboarding.exception.AccountNotFoundException;
import com.nl.customerOnboarding.exception.CustomerNotAddedException;
import com.nl.customerOnboarding.model.AccountDetails;
import com.nl.customerOnboarding.model.CustomerDetails;
import com.nl.customerOnboarding.model.User;
import com.nl.customerOnboarding.repository.AccountRepository;
import com.nl.customerOnboarding.repository.CustomerRepository;
import com.nl.customerOnboarding.util.RandomPasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerOnboardingService;

    @Mock
    private CustomerRepository customerOnboardingRepository;

    @Mock
    private AccountRepository accountRepository;


    String password = RandomPasswordGenerator.generatePassword();

    @Test
    public void registerNewCustomer_successful_test() {
        Map<String, String> credentials = new HashMap<>();
        LocalDate date = LocalDate.of(1994, 2, 10);
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
        when(customerOnboardingRepository.save(customer)).thenReturn(customer);
        CustomerDetails details = customerOnboardingService.registerCustomer(customer);
        Assertions.assertEquals(1002, details.getAccount().getAccountNumber());
        Assertions.assertNotEquals(null, details);

    }

    @Test
    public void registerNewCustomer_Unsuccessful_countryNa_test() {
        Map<String, String> credentials = new HashMap<>();
        LocalDate date = LocalDate.of(1994, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "Belgium",
                null);
        Exception e = Assertions.assertThrows(CustomerNotAddedException.class, () -> customerOnboardingService.registerCustomer(customer));

    }

    @Test()
    public void registerNewCustomer_Unsuccessful_age_test() {
        Map<String, String> credentials = new HashMap<>();
        LocalDate date = LocalDate.of(2022, 2, 10);
        Customer customer = new Customer(
                "John Doe",
                "john.doe@gmail.com",
                date, "India",
                null);
        Exception e = Assertions.assertThrows(CustomerNotAddedException.class, () -> customerOnboardingService.registerCustomer(customer));
    }

    @Test
    public void loginSuccessfulTest() {
        User user = new User("john.doe@gmail.com", password);
        Customer customer = new Customer("John Doe",
                "john.doe@gmail.com",
                LocalDate.of(2022, 2, 10), "India",
                password);
        when(customerOnboardingRepository.findByEmail(user.getUsername())).thenReturn(customer);
        Boolean loginValid = customerOnboardingService.loginCustomer(user);
        Assertions.assertEquals(true, loginValid);
    }

    @Test
    public void loginUnSuccessfulTest() {
        User user = new User("john@gmail.com", password + "xyz");
        when(customerOnboardingRepository.findByEmail(user.getUsername())).thenReturn(null);
        Boolean loginInalid = customerOnboardingService.loginCustomer(user);
        Assertions.assertEquals(false, loginInalid);
    }

    @Test
    public void getOverviewTest_Sucessful() {
        long accountNumber = 1001;
        Account account = new Account(
                1001,
                "Savings",
                500,
                "$",
                new Date(1994, 2, 10)
        );
        when(accountRepository.findByAccountNumber(1001)).thenReturn(account);
        AccountDetails accountDetails = customerOnboardingService.getOverview(1001);
        Assertions.assertEquals("Savings", accountDetails.getAccountType());
        Assertions.assertEquals(1001, accountDetails.getAccountNumber());

    }

    @Test
    public void getOverviewTest_UnSucessful() {
        long accountNumber = 1002;
        Exception e = Assertions.assertThrows(
                AccountNotFoundException.class,
                () -> customerOnboardingService.getOverview(accountNumber));

    }

}
