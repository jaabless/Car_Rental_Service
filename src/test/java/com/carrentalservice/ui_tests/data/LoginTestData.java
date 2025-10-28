package com.carrentalservice.ui_tests.data;

import java.util.stream.Stream;

public class LoginTestData {
    public static Stream<Object[]> validLoginTestData() {
        return Stream.of(
                new Object[]{"test.user@example.com", "ValidPass123!", true, "Login successful"},
                new Object[]{"newbusinessemail@gmail.com", "newBusiness1@", true, "Login successful"},
                new Object[]{"newcustomeremail@gmail.com", "newCustomer1@", true, "Login successful"}
        );
    }

    public static Stream<Object[]> invalidLoginTestData() {
        return Stream.of(
                new Object[]{"unregistered@email.com", "ValidPass123!", false, "Invalid credentials"},
                new Object[]{"newbusinessemail@gmail.com", "newBusiness", false, "Invalid credentials"},
                new Object[]{"newcustomeremail@gmail.com", "newCustomer1@", false, "Invalid credentials"},
                new Object[]{"", "ValidPass123!", false, "Email is required"},
                new Object[]{"newemail@email.com", "", false, "Password is required"}
        );
    }
}
