package com.nl.customerOnboarding.model;

import com.nl.customerOnboarding.domainObject.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AccountDetails {

    private long accountNumber;

    private String accountType;

    private double balance;

    private String currency;

    private Date accountOpenedOn;

    public static AccountDetails map(Account account) {
        return new AccountDetails(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBalance(),
                account.getCurrency(),
                account.getAccountOpenedOn()
        );
    }

}
