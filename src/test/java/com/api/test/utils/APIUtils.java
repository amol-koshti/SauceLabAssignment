package com.api.test.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Utility class for common API operations
 */
public class APIUtils {
    
    private static final String BASE_URL = "https://reqres.in";
    
    /**
     * Initialize Rest Assured configuration
     */
    public static void initializeRestAssured() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    
    /**
     * Create a basic request specification
     */
    public static RequestSpecification getBasicRequestSpec() {
        return RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON);
    }
    
    /**
     * Create login request body
     */
    public static String createLoginRequestBody(String email, String password) {
        return "{\n" +
            "    \"email\": \"" + email + "\",\n" +
            "    \"password\": \"" + password + "\"\n" +
            "}";
    }
    
    /**
     * Perform login request
     */
    public static Response performLogin(String email, String password) {
        String requestBody = createLoginRequestBody(email, password);
        
        return getBasicRequestSpec()
            .body(requestBody)
            .when()
            .post("/api/login");
    }
    
    /**
     * Validate successful login response
     */
    public static void validateSuccessfulLogin(Response response) {
        response.then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("token", org.hamcrest.Matchers.notNullValue())
            .body("token", org.hamcrest.Matchers.not(org.hamcrest.Matchers.emptyString()));
    }
    
    /**
     * Validate failed login response
     */
    public static void validateFailedLogin(Response response) {
        response.then()
            .statusCode(400)
            .contentType(ContentType.JSON)
            .body("error", org.hamcrest.Matchers.notNullValue());
    }
    
    /**
     * Print response details for debugging
     */
    public static void printResponseDetails(Response response, String testName) {
        System.out.println("=== " + testName + " ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asPrettyString());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("================================");
    }
    
    /**
     * Validate response time is within acceptable limits
     */
    public static void validateResponseTime(Response response, long maxTimeMs) {
        long responseTime = response.getTime();
        if (responseTime > maxTimeMs) {
            throw new AssertionError("Response time " + responseTime + "ms exceeds maximum " + maxTimeMs + "ms");
        }
    }
} 