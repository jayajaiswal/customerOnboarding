package com.nl.customerOnboarding.repository;

import com.nl.customerOnboarding.domainObject.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Account findByAccountNumber(long accountNumber);
}
