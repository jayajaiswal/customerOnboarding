package com.nl.customerOnboarding.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomPasswordGeneratorTest {

    @Test
    public void generatePasswordTest() {

        RandomPasswordGenerator gen = new RandomPasswordGenerator();
        String password = gen.generatePassword();
        Assertions.assertEquals(11, password.length());
    }
}
