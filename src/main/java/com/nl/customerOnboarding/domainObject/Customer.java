package com.nl.customerOnboarding.domainObject;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "country")
    private String country;

    @Column(name = "password")
    private String password;

    public Customer(String name, String email, Date dateOfBirth, String country, String password) {
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.password = password;
    }


}
