package com.carrentalservice.utils;


public class TestData {
    // Positive
    public static final String VALID_EMAIL = "test.user@example.com";
    public static final String VALID_PASSWORD = "ValidPass123!";

    // Negative / edge
    public static final String INVALID_PASSWORD = "wrongpassword";
    public static final String INVALID_EMAIL = "nonexistent@example";
    public static final String EMPTY = "";
    public static final String SQL_INJECTION = "admin' OR '1'='1";
    public static final String VERY_LONG_STRING = "a".repeat(30);
}

