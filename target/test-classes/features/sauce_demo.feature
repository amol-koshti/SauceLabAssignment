Feature: Sauce Demo E-commerce Application
  As a user of the Sauce Demo application
  I want to be able to login, browse products, add items to cart, and complete checkout
  So that I can successfully purchase products

  Background:
    Given I am on the Sauce Demo login page

  @smoke @login
  Scenario: Verify page title and successful login
    When I verify the page title is "Swag Labs"
    And I login with valid credentials
    Then I should be successfully logged in to the portal

  @smoke @shopping
  Scenario: Complete shopping flow with multiple items
    When I login with valid credentials
    And I add multiple items to the cart
    And I click on checkout
    And I provide checkout information
    And I click on finish
    Then I should see the confirmation message "THANK YOU FOR YOUR ORDER" 