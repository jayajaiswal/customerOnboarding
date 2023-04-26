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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repo;

    @Autowired
    AccountRepository accountRepo;

    public CustomerDetails registerCustomer(Customer customer) {
        try {
            if (customer.getCountry().equals("Netherlands") || customer.getCountry().equals("Belgium") || customer.getCountry().equals("Germany")) {
                throw new Exception("Members from " + customer.getCountry() + " not allowed to create an account");
            }
            if ((LocalDate.now().getYear() - customer.getDateOfBirth().getYear()) <= 18) {
                throw new Exception("Age should be greater than 18");
            }
            customer.setPassword(RandomPasswordGenerator.generatePassword());
            Customer newCustomer = (Customer) repo.save(customer);
            Account newAccount = new Account(1002, "Savings", 1000, "$", new Date(2000, 01, 7));
            accountRepo.save(newAccount);
            CustomerDetails customerDetails = new CustomerDetails(
                    newCustomer.getName(),
                    newCustomer.getEmail(),
                    newCustomer.getDateOfBirth(),
                    newCustomer.getCountry(),
                    newAccount,
                    newCustomer.getPassword());

            return customerDetails;
        } catch (Exception e) {
            throw new CustomerNotAddedException(e.getMessage());
        }

    }

    public Boolean loginCustomer(User user) {
        try {
            Customer customer = repo.findByEmail(user.getUsername());
            if (customer != null) {
                if (customer.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
            return false;
        }
        catch(Exception e){
            throw new AccountNotFoundException(e.getMessage());
        }
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
