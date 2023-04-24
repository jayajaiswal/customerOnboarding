package com.nl.customerOnboarding.util;

import java.util.Random;

public class RandomPasswordGenerator {

    private static String password = "";

    public static String generatePassword() {
        Random rand = new Random();
        password = "";
        for (int i = 1; i < 10; i++) {
            password += (char) (rand.nextInt(1, 26) + 'a');
        }
        password += rand.nextInt(1, 99);
        return password;
    }
}
