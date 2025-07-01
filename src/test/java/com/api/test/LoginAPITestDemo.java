package com.api.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * Demo Login API Test Class
 * Shows how to test a real login API with proper structure
 * This demonstrates the framework capabilities
 */
public class LoginAPITestDemo {
    
    // Using a mock API that simulates login behavior
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String LOGIN_ENDPOINT = "/posts";
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    /**
     * Positive Test Case: Valid login credentials
     * Demonstrates successful API call structure
     */
    @Test(description = "Positive Test - Valid login credentials demo")
    public void testValidLoginDemo() {
        String requestBody = "{\n" +
            "    \"email\": \"test@gmail.com\",\n" +
            "    \"password\": \"test\",\n" +
            "    \"title\": \"Login Request\",\n" +
            "    \"body\": \"This simulates a login request\",\n" +
            "    \"userId\": 1\n" +
            "}";
        
        Response response = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(201) // Created
            .contentType(ContentType.JSON)
            .body("id", notNullValue())
            .body("title", equalTo("Login Request"))
            .body("email", equalTo("test@gmail.com"))
            .extract().response();
        
        // Additional assertions
        Integer id = response.jsonPath().getInt("id");
        String title = response.jsonPath().getString("title");
        String email = response.jsonPath().getString("email");
        
        Assert.assertNotNull(id, "ID should not be null");
        Assert.assertEquals(title, "Login Request", "Title should match");
        Assert.assertEquals(email, "test@gmail.com", "Email should match");
        
        System.out.println("✅ Valid Login Demo Test Passed");
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Created ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Email: " + email);
    }
    
    /**
     * Negative Test Case: Invalid request
     * Demonstrates error handling structure
     */
    @Test(description = "Negative Test - Invalid request demo")
    public void testInvalidRequestDemo() {
        // Test with invalid endpoint to simulate error
        given()
            .contentType(ContentType.JSON)
            .body("{\"invalid\": \"data\"}")
        .when()
            .post("/invalid-endpoint")
        .then()
            .statusCode(404); // Not Found
        
        System.out.println("✅ Invalid Request Demo Test Passed");
    }
    
    /**
     * Test HTTP method validation
     */
    @Test(description = "HTTP Method Test - Using GET instead of POST")
    public void testHttpMethodValidation() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get(LOGIN_ENDPOINT)
        .then()
            .statusCode(200); // GET is allowed on this endpoint
        
        System.out.println("✅ HTTP Method Validation Test Passed");
    }
    
    /**
     * Test response time validation
     */
    @Test(description = "Performance Test - Response time validation")
    public void testResponseTimeValidation() {
        String requestBody = "{\n" +
            "    \"title\": \"Performance Test\",\n" +
            "    \"body\": \"Testing response time\",\n" +
            "    \"userId\": 1\n" +
            "}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(201)
            .time(lessThan(5000L)); // Response should be less than 5 seconds
        
        System.out.println("✅ Response Time Validation Test Passed");
    }
    
    /**
     * Test request/response logging
     */
    @Test(description = "Logging Test - Request and response logging")
    public void testRequestResponseLogging() {
        String requestBody = "{\n" +
            "    \"title\": \"Logging Test\",\n" +
            "    \"body\": \"Testing request/response logging\",\n" +
            "    \"userId\": 1\n" +
            "}";
        
        Response response = given()
            .log().all() // Log request details
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .log().all() // Log response details
            .statusCode(201)
            .extract().response();
        
        System.out.println("✅ Request/Response Logging Test Passed");
        System.out.println("Response ID: " + response.jsonPath().getInt("id"));
    }
} 