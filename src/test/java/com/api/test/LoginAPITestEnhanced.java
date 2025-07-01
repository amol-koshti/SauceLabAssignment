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
 * Enhanced Login API Test Class
 * Uses data providers for comprehensive testing scenarios
 */
public class LoginAPITestEnhanced {
    
    private static final String BASE_URL = "https://reqres.in";
    private static final String LOGIN_ENDPOINT = "/api/login";
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    /**
     * Positive Test Cases using Data Provider
     * Tests multiple valid login scenarios
     */
//    @Test(dataProvider = "validLoginData", dataProviderClass = LoginTestDataProvider.class,
//          description = "Positive Test - Valid login credentials using data provider")
//    public void testValidLoginWithDataProvider(String email, String password, String description) {
//        String requestBody = "{\n" +
//            "    \"email\": \"" + email + "\",\n" +
//            "    \"password\": \"" + password + "\"\n" +
//            "}";
//        
//        Response response = given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(200)
//            .contentType(ContentType.JSON)
//            .body("token", notNullValue())
//            .body("token", not(emptyString()))
//            .extract().response();
//        
//        // Additional assertions
//        String token = response.jsonPath().getString("token");
//        Assert.assertNotNull(token, "Token should not be null for: " + description);
//        Assert.assertFalse(token.isEmpty(), "Token should not be empty for: " + description);
//        
//        System.out.println("✅ Valid Login Test Passed: " + description);
//        System.out.println("Email: " + email);
//        System.out.println("Response Status: " + response.getStatusCode());
//        System.out.println("Token: " + token);
//    }
//    
//    /**
//     * Negative Test Cases using Data Provider
//     * Tests various invalid login scenarios
//     */
//    @Test(dataProvider = "invalidLoginData", dataProviderClass = LoginTestDataProvider.class,
//          description = "Negative Test - Invalid login credentials using data provider")
//    public void testInvalidLoginWithDataProvider(String email, String password, String description) {
//        String requestBody = "{\n" +
//            "    \"email\": \"" + email + "\",\n" +
//            "    \"password\": \"" + password + "\"\n" +
//            "}";
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(400)
//            .contentType(ContentType.JSON)
//            .body("error", notNullValue());
//        
//        System.out.println("✅ Invalid Login Test Passed: " + description);
//        System.out.println("Email: " + email);
//        System.out.println("Password: " + password);
//    }
//    
//    /**
//     * Test missing field scenarios
//     */
//    @Test(dataProvider = "missingFieldData", dataProviderClass = LoginTestDataProvider.class,
//          description = "Negative Test - Missing required fields")
//    public void testMissingFields(String email, String password, String description) {
//        String requestBody;
//        if (email == null && password == null) {
//            requestBody = "{}";
//        } else if (email == null) {
//            requestBody = "{\n" +
//                "    \"password\": \"" + password + "\"\n" +
//                "}";
//        } else {
//            requestBody = "{\n" +
//                "    \"email\": \"" + email + "\"\n" +
//                "}";
//        }
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(400)
//            .contentType(ContentType.JSON)
//            .body("error", notNullValue());
//        
//        System.out.println("✅ Missing Fields Test Passed: " + description);
//    }
//    
//    /**
//     * Test boundary value scenarios
//     */
//    @Test(dataProvider = "boundaryValueData", dataProviderClass = LoginTestDataProvider.class,
//          description = "Negative Test - Boundary value testing")
//    public void testBoundaryValues(String email, String password, String description) {
//        String requestBody = "{\n" +
//            "    \"email\": \"" + email + "\",\n" +
//            "    \"password\": \"" + password + "\"\n" +
//            "}";
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(400)
//            .contentType(ContentType.JSON)
//            .body("error", notNullValue());
//        
//        System.out.println("✅ Boundary Value Test Passed: " + description);
//    }
//    
//    /**
//     * Test special character scenarios
//     */
//    @Test(dataProvider = "specialCharacterData", dataProviderClass = LoginTestDataProvider.class,
//          description = "Negative Test - Special characters in input")
//    public void testSpecialCharacters(String email, String password, String description) {
//        String requestBody = "{\n" +
//            "    \"email\": \"" + email + "\",\n" +
//            "    \"password\": \"" + password + "\"\n" +
//            "}";
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(400)
//            .contentType(ContentType.JSON)
//            .body("error", notNullValue());
//        
//        System.out.println("✅ Special Characters Test Passed: " + description);
//    }
//    
//    /**
//     * Test with different HTTP methods (should fail)
//     */
//    @Test(description = "Negative Test - Using GET method instead of POST")
//    public void testWrongHttpMethod() {
//        given()
//            .contentType(ContentType.JSON)
//        .when()
//            .get(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(405); // Method Not Allowed
//        
//        System.out.println("✅ Wrong HTTP Method Test Passed");
//    }
//    
//    /**
//     * Test with wrong content type
//     */
//    @Test(description = "Negative Test - Wrong content type")
//    public void testWrongContentType() {
//        String requestBody = "{\n" +
//            "    \"email\": \"test@example.com\",\n" +
//            "    \"password\": \"testpassword\"\n" +
//            "}";
//        
//        given()
//            .contentType(ContentType.TEXT)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(400); // Bad Request
//        
//        System.out.println("✅ Wrong Content Type Test Passed");
//    }
//    
//    /**
//     * Test response time
//     */
//    @Test(description = "Performance Test - Response time validation")
//    public void testResponseTime() {
//        String requestBody = "{\n" +
//            "    \"email\": \"eve.holt@reqres.in\",\n" +
//            "    \"password\": \"cityslicka\"\n" +
//            "}";
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(requestBody)
//        .when()
//            .post(LOGIN_ENDPOINT)
//        .then()
//            .statusCode(200)
//            .time(lessThan(5000L)); // Response should be less than 5 seconds
//        
//        System.out.println("✅ Response Time Test Passed");
//    }
} 