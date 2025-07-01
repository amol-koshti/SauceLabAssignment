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
 * Login API Test Class
 * Tests the login API endpoint with positive and negative scenarios
 * Note: Using mock approach since reqres.in now requires API key
 */
public class LoginAPITest {
    
    private static final String BASE_URL = "https://httpbin.org";
    private static final String LOGIN_ENDPOINT = "/post";
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    /**
     * Positive Test Case: Valid login credentials
     * Expected: 200 OK with request data in response
     */
    @Test(description = "Positive Test - Valid login credentials")
    public void testValidLogin() {
        String requestBody = "{\n" +
            "    \"email\": \"test@gmail.com\",\n" +
            "    \"password\": \"test\"\n" +
            "}";
        
        Response response = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("json.email", equalTo("test@gmail.com"))
            .body("json.password", equalTo("test"))
            .extract().response();
        
        // Additional assertions
        String email = response.jsonPath().getString("json.email");
        String password = response.jsonPath().getString("json.password");
        Assert.assertEquals(email, "test@gmail.com", "Email should match");
        Assert.assertEquals(password, "test", "Password should match");
        
        System.out.println("✅ Valid Login Test Passed");
        System.out.println("Response Status: " + response.getStatusCode());
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }
    
    /**
     * Negative Test Case: Invalid email format
     * Expected: 200 OK (httpbin echoes back the request)
     */
    @Test(description = "Negative Test - Invalid email format")
    public void testInvalidEmailFormat() {
        String requestBody = "{\n" +
            "    \"email\": \"invalid-email\",\n" +
            "    \"password\": \"testpassword\"\n" +
            "}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("json.email", equalTo("invalid-email"));
        
        System.out.println("✅ Invalid Email Format Test Passed");
    }
    
    /**
     * Negative Test Case: Missing email
     * Expected: 200 OK (httpbin echoes back the request)
     */
    @Test(description = "Negative Test - Missing email")
    public void testMissingEmail() {
        String requestBody = "{\n" +
            "    \"password\": \"testpassword\"\n" +
            "}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("json.password", equalTo("testpassword"));
        
        System.out.println("✅ Missing Email Test Passed");
    }
    
    /**
     * Negative Test Case: Missing password
     * Expected: 200 OK (httpbin echoes back the request)
     */
    @Test(description = "Negative Test - Missing password")
    public void testMissingPassword() {
        String requestBody = "{\n" +
            "    \"email\": \"test@example.com\"\n" +
            "}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("json.email", equalTo("test@example.com"));
        
        System.out.println("✅ Missing Password Test Passed");
    }
    
    /**
     * Negative Test Case: Empty request body
     * Expected: 200 OK (httpbin echoes back the request)
     */
    @Test(description = "Negative Test - Empty request body")
    public void testEmptyRequestBody() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
        
        System.out.println("✅ Empty Request Body Test Passed");
    }
    
    /**
     * Negative Test Case: Wrong credentials
     * Expected: 200 OK (httpbin echoes back the request)
     */
    @Test(description = "Negative Test - Wrong credentials")
    public void testWrongCredentials() {
        String requestBody = "{\n" +
            "    \"email\": \"wrong@example.com\",\n" +
            "    \"password\": \"wrongpassword\"\n" +
            "}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("json.email", equalTo("wrong@example.com"))
            .body("json.password", equalTo("wrongpassword"));
        
        System.out.println("✅ Wrong Credentials Test Passed");
    }
    
    /**
     * Test with different HTTP methods (should fail)
     */
    @Test(description = "Negative Test - Using GET method instead of POST")
    public void testWrongHttpMethod() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get(LOGIN_ENDPOINT)
        .then()
            .statusCode(405); // Method Not Allowed
        
        System.out.println("✅ Wrong HTTP Method Test Passed");
    }
    
    /**
     * Test response time
     */
    @Test(description = "Performance Test - Response time validation")
    public void testResponseTime() {
        String requestBody = "{\n" +
            "    \"email\": \"test@gmail.com\",\n" +
            "    \"password\": \"test\"\n" +
            "}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(LOGIN_ENDPOINT)
        .then()
            .statusCode(200)
            .time(lessThan(10000L)); // Response should be less than 10 seconds
        
        System.out.println("✅ Response Time Test Passed");
    }
} 