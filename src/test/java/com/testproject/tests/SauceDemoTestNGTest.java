package com.testproject.tests;

import com.testproject.pages.*;
import com.testproject.config.TestConfig;
import com.testproject.utils.ScreenshotUtil;
import com.testproject.utils.TestDataUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.Duration;

public class SauceDemoTestNGTest extends BaseTest {
    private WebDriverWait wait;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUpWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT));
    }

    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            String filePath = ScreenshotUtil.captureScreenshot(driver, result.getName());
            ScreenshotUtil.attachScreenshotToReport(filePath);
        }
    }

    @Test(description = "Verify page title and successful login with DataProvider")
    public void testLoginAndTitleVerification() {
        loginPage = new LoginPage(driver);
        // Demonstrate reading username from a properties file (if available)
        String username = TestDataUtil.getProperty("testdata.properties", "username");
        if (username == null) username = TestConfig.VALID_USERNAME;
        String password = TestConfig.VALID_PASSWORD;
        String actualTitle = loginPage.getPageTitle();
        assertThat(actualTitle).isEqualTo(TestConfig.EXPECTED_TITLE);
        loginPage.login(username, password);
        inventoryPage = new InventoryPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
    }

    @Test(description = "Complete shopping flow with multiple items")
    public void testCompleteShoppingFlow() {
        loginPage = new LoginPage(driver);
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
        inventoryPage.addMultipleItemsToCart(3);
        String cartCount = inventoryPage.getCartItemCount();
        assertThat(cartCount).isEqualTo("3");
        inventoryPage.clickShoppingCart();
        cartPage = new CartPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("checkout")));
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("first-name")));
        checkoutPage.fillCheckoutInformation(
            TestConfig.FIRST_NAME, 
            TestConfig.LAST_NAME, 
            TestConfig.POSTAL_CODE
        );
        checkoutPage.clickContinue();
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("finish")));
        checkoutPage.clickFinish();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("complete-header")));
        String actualMessage = checkoutPage.getCompleteHeaderText();
        assertThat(actualMessage).isEqualTo(TestConfig.EXPECTED_CONFIRMATION_MESSAGE);
    }

    @Test(dataProvider = "loginCredentials", description = "Test login with multiple users using DataProvider")
    public void testLoginWithDataProvider(String username, String password) {
        loginPage = new LoginPage(driver);
        String actualTitle = loginPage.getPageTitle();
        assertThat(actualTitle).isEqualTo(TestConfig.EXPECTED_TITLE);
        loginPage.login(username, password);
        inventoryPage = new InventoryPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
    }

    @Test(description = "Test adding individual items to cart")
    public void testAddIndividualItemsToCart() {
        loginPage = new LoginPage(driver);
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
        inventoryPage.addItemToCart(0);
        String cartCount1 = inventoryPage.getCartItemCount();
        assertThat(cartCount1).isEqualTo("1");
        inventoryPage.addItemToCart(1);
        String cartCount2 = inventoryPage.getCartItemCount();
        assertThat(cartCount2).isEqualTo("2");
    }

    @Test(description = "Test checkout process with validation")
    public void testCheckoutProcess() {
        loginPage = new LoginPage(driver);
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        inventoryPage.addMultipleItemsToCart(2);
        inventoryPage.clickShoppingCart();
        cartPage = new CartPage(driver);
        assertThat(cartPage.isCartPageDisplayed()).isTrue();
        assertThat(cartPage.getCartItemCount()).isEqualTo(2);
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("first-name")));
        checkoutPage.fillCheckoutInformation(
            TestConfig.FIRST_NAME, 
            TestConfig.LAST_NAME, 
            TestConfig.POSTAL_CODE
        );
        checkoutPage.clickContinue();
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("finish")));
        checkoutPage.clickFinish();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("complete-header")));
        assertThat(checkoutPage.isCheckoutCompletePageDisplayed()).isTrue();
        assertThat(checkoutPage.getCompleteHeaderText()).isEqualTo(TestConfig.EXPECTED_CONFIRMATION_MESSAGE);
    }

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        return TestConfig.LOGIN_CREDENTIALS;
    }
} 