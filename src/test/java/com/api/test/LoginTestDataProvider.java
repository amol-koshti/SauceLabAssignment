package com.api.test;

import org.testng.annotations.DataProvider;

/**
 * Data Provider class for Login API tests
 * Provides test data for various positive and negative scenarios
 */
public class LoginTestDataProvider {
    
    @DataProvider(name = "validLoginData")
    public static Object[][] getValidLoginData() {
        return new Object[][] {
            {"eve.holt@reqres.in", "cityslicka", "Valid credentials from reqres.in"},
            {"test@gmail.com", "test", "Test credentials as per requirement"}
        };
    }
    
    @DataProvider(name = "invalidLoginData")
    public static Object[][] getInvalidLoginData() {
        return new Object[][] {
            {"invalid-email", "password123", "Invalid email format"},
            {"test@", "password123", "Incomplete email format"},
            {"@example.com", "password123", "Missing username in email"},
            {"test.example.com", "password123", "Missing @ symbol"},
            {"", "password123", "Empty email"},
            {"test@example.com", "", "Empty password"},
            {"", "", "Both fields empty"},
            {"null", "password123", "Null email string"},
            {"test@example.com", "null", "Null password string"}
        };
    }
    
    @DataProvider(name = "missingFieldData")
    public static Object[][] getMissingFieldData() {
        return new Object[][] {
            {"test@example.com", null, "Missing password field"},
            {null, "password123", "Missing email field"},
            {null, null, "Both fields missing"}
        };
    }
    
    @DataProvider(name = "boundaryValueData")
    public static Object[][] getBoundaryValueData() {
        return new Object[][] {
            {"a".repeat(1000) + "@example.com", "password123", "Very long email"},
            {"a@b.c", "password123", "Very short email"},
            {"test@example.com", "a".repeat(1000), "Very long password"},
            {"test@example.com", "a", "Very short password"}
        };
    }
    
    @DataProvider(name = "specialCharacterData")
    public static Object[][] getSpecialCharacterData() {
        return new Object[][] {
            {"test+tag@example.com", "password123", "Email with plus sign"},
            {"test.tag@example.com", "password123", "Email with dot"},
            {"test-tag@example.com", "password123", "Email with hyphen"},
            {"test@example.com", "pass@word123", "Password with special characters"},
            {"test@example.com", "pass word123", "Password with space"},
            {"test@example.com", "pass\nword123", "Password with newline"},
            {"test@example.com", "pass\tword123", "Password with tab"}
        };
    }
} 