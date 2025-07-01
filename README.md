# Rest Assured API Automation Framework

This project implements a comprehensive API automation framework using Rest Assured for testing the login API endpoint.

## Project Overview

The framework tests the login API endpoint `https://reqres.in/api/login` with both positive and negative test scenarios using POST method.

### API Details
- **URL**: `https://reqres.in/api/login`
- **Method**: POST
- **Content-Type**: application/json

### Request Format
```json
{
    "email": "test@gmail.com",
    "password": "test"
}
```

## Project Structure

```
RestAssuredProject/
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── api/
│                   └── test/
│                       ├── LoginAPITest.java              # Basic test cases
│                       ├── LoginAPITestEnhanced.java      # Enhanced test cases with data providers
│                       ├── LoginTestDataProvider.java     # Test data providers
│                       └── utils/
│                           └── APIUtils.java              # Utility methods
├── pom.xml                                                 # Maven dependencies
├── testng.xml                                             # TestNG configuration
└── README.md                                              # This file
```

## Test Scenarios

### Positive Test Cases
1. **Valid Login Credentials**: Tests successful login with valid email and password
   - Expected: HTTP 200 OK with token in response
   - Uses credentials from reqres.in API

### Negative Test Cases
1. **Invalid Email Format**: Tests with malformed email addresses
2. **Missing Email**: Tests without email field
3. **Missing Password**: Tests without password field
4. **Empty Request Body**: Tests with empty JSON object
5. **Wrong Credentials**: Tests with incorrect login details
6. **Null Values**: Tests with null email/password
7. **Very Long Email**: Tests with extremely long email addresses
8. **Boundary Values**: Tests with minimum/maximum length inputs
9. **Special Characters**: Tests with special characters in inputs
10. **Wrong HTTP Method**: Tests using GET instead of POST
11. **Wrong Content Type**: Tests with incorrect content type
12. **Response Time**: Performance testing

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Internet connection (for API calls)

## Setup Instructions

1. **Clone or download the project**
2. **Navigate to project directory**:
   ```bash
   cd RestAssuredProject
   ```

3. **Install dependencies**:
   ```bash
   mvn clean install
   ```

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
# Run basic tests only
mvn test -Dtest=LoginAPITest

# Run enhanced tests only
mvn test -Dtest=LoginAPITestEnhanced
```

### Run Tests with TestNG XML
```bash
# Run all tests
mvn test -DsuiteXmlFile=testng.xml

# Run specific test suite
mvn test -DsuiteXmlFile=testng.xml -Dtest=Basic Login API Tests
```

### Run from IDE
1. Open the project in your IDE (Eclipse, IntelliJ IDEA, etc.)
2. Right-click on `testng.xml` and select "Run As" > "TestNG Suite"
3. Or right-click on individual test classes to run them

## Test Execution Details

### Basic Tests (`LoginAPITest.java`)
- Contains 8 test methods
- Tests fundamental positive and negative scenarios
- Manual test data definition

### Enhanced Tests (`LoginAPITestEnhanced.java`)
- Contains 8 test methods with data providers
- Comprehensive testing with multiple data sets
- Uses `LoginTestDataProvider` for test data

### Data Providers
- `validLoginData`: Multiple valid credential combinations
- `invalidLoginData`: Various invalid input scenarios
- `missingFieldData`: Missing required fields
- `boundaryValueData`: Boundary value testing
- `specialCharacterData`: Special character handling

## Assertions and Validations

### HTTP Status Codes
- **200 OK**: Successful login
- **400 Bad Request**: Invalid credentials or missing fields
- **405 Method Not Allowed**: Wrong HTTP method

### Response Validations
- **Content-Type**: application/json
- **Token Presence**: Valid login returns non-null, non-empty token
- **Error Message**: Failed login returns error field
- **Response Time**: Performance validation (max 5 seconds)

## Dependencies

- **Rest Assured 5.3.0**: API testing framework
- **TestNG 7.7.1**: Testing framework
- **Jackson 2.15.2**: JSON processing
- **JSON Schema Validator**: Schema validation
- **SLF4J**: Logging

## Test Reports

After test execution, TestNG generates HTML reports in the `target/surefire-reports` directory.

## Troubleshooting

### Common Issues

1. **Connection Issues**: Ensure internet connectivity for API calls
2. **Java Version**: Verify Java 11+ is installed and configured
3. **Maven Issues**: Run `mvn clean install` to resolve dependency issues

### Debug Mode
Enable detailed logging by adding to test methods:
```java
RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
```

## API Documentation

The tested API is part of the reqres.in service, which provides a mock REST API for testing purposes.

- **Base URL**: https://reqres.in
- **Login Endpoint**: /api/login
- **Valid Credentials**: eve.holt@reqres.in / cityslicka

## Contributing

1. Follow the existing code structure
2. Add comprehensive test cases for new scenarios
3. Update documentation for any changes
4. Ensure all tests pass before committing

## License

This project is for educational and testing purposes. 