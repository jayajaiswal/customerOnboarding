package com.nl.customerOnboarding.model;

import com.nl.customerOnboarding.domainObject.Account;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CustomerDetails {
    private String name;
    private String email;
    private Date dateOfBirth;
    private String country;
    private Account account;
    private String password;
}
