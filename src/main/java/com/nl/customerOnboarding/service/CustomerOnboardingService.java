package com.nl.customerOnboarding.service;

import com.nl.customerOnboarding.domainObject.Account;
import com.nl.customerOnboarding.domainObject.Customer;
import com.nl.customerOnboarding.exception.AccountNotFoundException;
import com.nl.customerOnboarding.exception.CustomerNotAddedException;
import com.nl.customerOnboarding.model.AccountDetails;
import com.nl.customerOnboarding.model.CustomerDetails;
import com.nl.customerOnboarding.model.User;
import com.nl.customerOnboarding.repository.AccountRepository;
import com.nl.customerOnboarding.repository.CustomerOnboardingRepository;
import com.nl.customerOnboarding.util.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerOnboardingService {

    @Autowired
    CustomerOnboardingRepository repo;

    @Autowired
    AccountRepository accountRepo;

    public Map<String, String> registerCustomer(CustomerDetails customer) {

        try {
            if (customer.getCountry().equals("Netherlands") || customer.getCountry().equals("Belgium") || customer.getCountry().equals("Germany")) {
                throw new Exception("Members from " + customer.getCountry() + " not allowed to create an account");
            }
            if ((LocalDate.now().getYear() - customer.getDateOfBirth().toLocalDate().getYear()) <= 18) {
                throw new Exception("Age should be greater than 18");
            }
            Map<String, String> loginCredentials = new HashMap<>();
            Customer newCustomer = (Customer) repo.save
                    (new Customer(
                            customer.getName(),
                            customer.getEmail(),
                            customer.getDateOfBirth(),
                            customer.getCountry(),
                            RandomPasswordGenerator.generatePassword()
                    ));

            loginCredentials.put(newCustomer.getEmail(), newCustomer.getPassword());
            Account newAccount = new Account(1002, "Savings", 1000, "$", new Date(2000, 01, 7));
            accountRepo.save(newAccount);

            return loginCredentials;
        } catch (Exception e) {
            throw new CustomerNotAddedException(e.getMessage());
        }

    }

    public Boolean loginCustomer(User user) {

        Customer customer = repo.findByEmail(user.getUsername());
        if (customer != null) {
            if (customer.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public AccountDetails getOverview(long accountNumber) {

        try {
            Account account = accountRepo.findByAccountNumber(accountNumber);
            return AccountDetails.map(account);
        } catch (Exception e) {
            throw new AccountNotFoundException(e.getMessage());
        }

    }
}
