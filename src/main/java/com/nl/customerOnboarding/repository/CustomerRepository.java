package com.nl.customerOnboarding.repository;

import com.nl.customerOnboarding.domainObject.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    Customer findByEmail(String email);

}
