package com.nl.customerOnboarding.domainObject;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "account_no")
    private long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private double balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "account_opened_on")
    private Date accountOpenedOn;

    public Account(long accountNumber, String accountType, double balance, String currency, Date accountOpenedOn) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
        this.accountOpenedOn = accountOpenedOn;
    }
}
