# End-to-End Test Cases for Sauce Demo

## Test Case 1: Login and Title Verification
- **Objective:** Verify the application title and successful login with valid credentials.
- **Steps:**
  1. Launch the application URL.
  2. Verify the page title is "Swag Labs".
  3. Enter valid username and password.
  4. Click Login.
  5. Verify successful login by checking inventory page is displayed.
- **Expected Result:** User is logged in and inventory page is visible.

## Test Case 2: Add Multiple Items to Cart
- **Objective:** Verify that multiple items can be added to the cart.
- **Steps:**
  1. Login as a valid user.
  2. Add three items to the cart.
  3. Verify the cart badge shows 3 items.
- **Expected Result:** Cart badge displays correct item count.

## Test Case 3: Checkout Process
- **Objective:** Verify the checkout process with valid information.
- **Steps:**
  1. Add items to cart.
  2. Click the cart icon and proceed to checkout.
  3. Enter first name, last name, and postal code.
  4. Click Continue.
  5. Click Finish.
  6. Verify the confirmation message is "Thank you for your order!".
- **Expected Result:** Order is placed and confirmation message is displayed.

## Test Case 4: Login with Multiple Users
- **Objective:** Verify login functionality with different user credentials.
- **Steps:**
  1. Attempt login with each user from the data provider.
  2. Verify successful login for each.
- **Expected Result:** All valid users can log in successfully.

## Test Case 5: Add and Remove Items from Cart
- **Objective:** Verify that items can be added and removed from the cart.
- **Steps:**
  1. Add items to cart.
  2. Remove one or more items.
  3. Verify the cart badge updates accordingly.
- **Expected Result:** Cart reflects correct item count after removal.

---

# Risk Mitigation Plan

| Risk | Impact | Likelihood | Mitigation Strategy |
|------|--------|------------|---------------------|
| Application downtime | High | Medium | Run tests during off-peak hours; implement retries for critical flows |
| Test data changes | Medium | Medium | Use stable test accounts; reset data before/after tests |
| UI changes | High | Medium | Use robust locators; update tests promptly on UI changes |
| Browser compatibility | Medium | Low | Run tests on multiple browsers using WebDriverManager |
| WebDriver version mismatch | Medium | Medium | Use WebDriverManager to auto-manage drivers |
| Network issues | Medium | Medium | Implement retries and timeouts; run tests on stable network |
| Flaky tests | High | Medium | Use explicit waits; review and stabilize flaky tests |
| Sensitive data exposure | High | Low | Store credentials securely; avoid logging sensitive info |

---

**Note:**
- All test cases are automated in the TestNG suite.
- Risk mitigation is reviewed regularly and updated as needed. 