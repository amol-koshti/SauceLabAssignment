package com.testproject.config;

public class TestConfig {
    
    // Application URLs
    public static final String BASE_URL = "https://www.saucedemo.com/";
    
    // Test Data
    public static final String VALID_USERNAME = "standard_user";
    public static final String VALID_PASSWORD = "secret_sauce";
    public static final String EXPECTED_TITLE = "Swag Labs";
    public static final String EXPECTED_CONFIRMATION_MESSAGE = "Thank you for your order!";
    
    // Checkout Information
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String POSTAL_CODE = "12345";
    
    // Timeouts
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 10;
    
    // Test Data for DataProvider
    public static final Object[][] LOGIN_CREDENTIALS = {
        {"standard_user", "secret_sauce"},
        {"problem_user", "secret_sauce"},
        {"performance_glitch_user", "secret_sauce"}
    };
} 