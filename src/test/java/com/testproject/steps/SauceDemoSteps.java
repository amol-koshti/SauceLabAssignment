package com.testproject.steps;

import com.testproject.pages.*;
import com.testproject.config.TestConfig;
import com.testproject.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.Duration;

public class SauceDemoSteps {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    public SauceDemoSteps() {
        this.driver = Hooks.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT));
    }
    
    @Given("I am on the Sauce Demo login page")
    public void i_am_on_the_sauce_demo_login_page() {
        driver.get(TestConfig.BASE_URL);
        loginPage = new LoginPage(driver);
    }
    
    @When("I verify the page title is {string}")
    public void i_verify_the_page_title_is(String expectedTitle) {
        String actualTitle = loginPage.getPageTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
    
    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
    }
    
    @Then("I should be successfully logged in to the portal")
    public void i_should_be_successfully_logged_in_to_the_portal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
    }
    
    @When("I add multiple items to the cart")
    public void i_add_multiple_items_to_the_cart() {
        // Add 3 items to cart
        inventoryPage.addMultipleItemsToCart(3);
    }
    
    @When("I click on checkout")
    public void i_click_on_checkout() {
        inventoryPage.clickShoppingCart();
        cartPage = new CartPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("checkout")));
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
    }
    
    @When("I provide checkout information")
    public void i_provide_checkout_information() {
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("first-name")));
        checkoutPage.fillCheckoutInformation(
            TestConfig.FIRST_NAME, 
            TestConfig.LAST_NAME, 
            TestConfig.POSTAL_CODE
        );
        checkoutPage.clickContinue();
    }
    
    @When("I click on finish")
    public void i_click_on_finish() {
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("finish")));
        checkoutPage.clickFinish();
    }
    
    @Then("I should see the confirmation message {string}")
    public void i_should_see_the_confirmation_message(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("complete-header")));
        String actualMessage = checkoutPage.getCompleteHeaderText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
    
    // TestNG DataProvider for credentials
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        return TestConfig.LOGIN_CREDENTIALS;
    }
} 