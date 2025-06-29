# Sauce Demo TestNG Framework

This project implements a comprehensive TestNG framework using Java Selenium WebDriver for testing the Sauce Demo e-commerce application.

## Project Structure

```
TestProject/
├── src/
│   ├── main/java/com/testproject/
│   │   ├── pages/           # Page Object Model classes
│   │   └── config/          # Configuration and constants
│   └── test/
│       ├── java/com/testproject/
│       │   ├── tests/       # TestNG test classes
│       │   └── steps/       # Cucumber step definitions (legacy)
│       └── resources/
│           └── features/    # Gherkin feature files (legacy)
├── pom.xml                  # Maven dependencies
├── testng.xml              # TestNG configuration
└── README.md               # This file
```

## Features Implemented

1. **Page Title Verification**: Verify the page title is "Swag Labs"
2. **Login Functionality**: Login with valid credentials using TestNG @DataProvider
3. **Shopping Cart**: Add multiple items to the cart
4. **Checkout Process**: Complete checkout with user information
5. **Order Confirmation**: Verify the confirmation message "Thank you for your order!"

## Test Scenarios

The framework includes the following TestNG test methods:

### 1. `testLoginAndTitleVerification()`
- Verifies page title is "Swag Labs"
- Logs in with valid credentials
- Confirms successful login

### 2. `testCompleteShoppingFlow()`
- Complete end-to-end shopping flow
- Adds 3 items to cart
- Completes checkout process
- Verifies order confirmation

### 3. `testLoginWithDataProvider(String username, String password)`
- Tests login with multiple user credentials using @DataProvider
- Tests: standard_user, problem_user, performance_glitch_user

### 4. `testAddIndividualItemsToCart()`
- Tests adding individual items to cart
- Verifies cart count after each addition

### 5. `testCheckoutProcess()`
- Comprehensive checkout process validation
- Verifies each step of the checkout flow

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome browser (WebDriverManager will automatically download the appropriate ChromeDriver)

## Dependencies

- **Selenium WebDriver**: For browser automation
- **TestNG**: For test execution and DataProvider
- **WebDriverManager**: For automatic WebDriver management
- **AssertJ**: For fluent assertions

## Running the Tests

### Option 1: Run via Maven Command Line

```bash
# Run all tests using TestNG XML
mvn test

# Run specific test class
mvn test -Dtest=SauceDemoTestNGTest

# Run specific test method
mvn test -Dtest=SauceDemoTestNGTest#testLoginAndTitleVerification
```

### Option 2: Run via TestNG XML

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Option 3: Run via IDE

1. Right-click on `SauceDemoTestNGTest.java` and select "Run as TestNG Test"
2. Or right-click on `testng.xml` and select "Run as TestNG Suite"

## Test Data

The framework uses TestNG DataProvider to test with multiple user credentials:

- `standard_user` / `secret_sauce`
- `problem_user` / `secret_sauce`
- `performance_glitch_user` / `secret_sauce`

## Page Object Model

The framework follows the Page Object Model design pattern:

- **LoginPage**: Handles login functionality
- **InventoryPage**: Manages product inventory and cart operations
- **CartPage**: Handles shopping cart operations
- **CheckoutPage**: Manages checkout process

## Reports

After test execution, reports are generated in:
- `target/surefire-reports/` - TestNG HTML reports
- `target/surefire-reports/index.html` - Main test report

## Configuration

Test configuration is centralized in `TestConfig.java`:
- Base URL: https://www.saucedemo.com/
- Test data constants
- Timeout values
- Credentials for DataProvider

## Key Features

1. **TestNG Framework**: Uses TestNG for test execution and reporting
2. **Page Object Model**: Maintainable and reusable page objects
3. **DataProvider**: TestNG DataProvider for parameterized testing
4. **Explicit Waits**: Proper synchronization with WebDriverWait
5. **Assertions**: Fluent assertions using AssertJ
6. **WebDriver Management**: Automatic WebDriver setup using WebDriverManager
7. **Comprehensive Testing**: Covers all aspects of the e-commerce flow

## Test Execution Results

```
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
```

The framework includes:
- 1 test for login and title verification
- 1 test for complete shopping flow
- 3 tests for DataProvider login (one for each user)
- 1 test for individual cart operations
- 1 test for checkout process validation

## Troubleshooting

1. **ChromeDriver Issues**: WebDriverManager will automatically download the correct ChromeDriver version
2. **Element Not Found**: Check if the page has loaded completely using explicit waits
3. **Test Failures**: Verify the Sauce Demo application is accessible and stable

## Contributing

1. Follow the existing code structure
2. Add new page objects in the `pages` package
3. Create new test methods in `SauceDemoTestNGTest.java`
4. Add appropriate test data in `TestConfig.java`
5. Update `testng.xml` if adding new test classes 