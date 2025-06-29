package com.testproject.tests;

import com.testproject.pages.*;
import com.testproject.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.Duration;

public class SauceDemoTestNGTest {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT));
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test(description = "Verify page title and successful login with DataProvider")
    public void testLoginAndTitleVerification() {
        // Navigate to login page
        driver.get(TestConfig.BASE_URL);
        loginPage = new LoginPage(driver);
        
        // Verify page title
        String actualTitle = loginPage.getPageTitle();
        assertThat(actualTitle).isEqualTo(TestConfig.EXPECTED_TITLE);
        
        // Login with valid credentials
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        
        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
    }
    
    @Test(description = "Complete shopping flow with multiple items")
    public void testCompleteShoppingFlow() {
        // Navigate to login page
        driver.get(TestConfig.BASE_URL);
        loginPage = new LoginPage(driver);
        
        // Login with valid credentials
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        
        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
        
        // Add multiple items to cart
        inventoryPage.addMultipleItemsToCart(3);
        
        // Verify items were added to cart
        String cartCount = inventoryPage.getCartItemCount();
        assertThat(cartCount).isEqualTo("3");
        
        // Click on checkout
        inventoryPage.clickShoppingCart();
        cartPage = new CartPage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("checkout")));
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        
        // Provide checkout information
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("first-name")));
        checkoutPage.fillCheckoutInformation(
            TestConfig.FIRST_NAME, 
            TestConfig.LAST_NAME, 
            TestConfig.POSTAL_CODE
        );
        checkoutPage.clickContinue();
        
        // Click on finish
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("finish")));
        checkoutPage.clickFinish();
        
        // Verify confirmation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("complete-header")));
        String actualMessage = checkoutPage.getCompleteHeaderText();
        assertThat(actualMessage).isEqualTo("Thank you for your order!");
    }
    
    @Test(dataProvider = "loginCredentials", description = "Test login with multiple users using DataProvider")
    public void testLoginWithDataProvider(String username, String password) {
        // Navigate to login page
        driver.get(TestConfig.BASE_URL);
        loginPage = new LoginPage(driver);
        
        // Verify page title
        String actualTitle = loginPage.getPageTitle();
        assertThat(actualTitle).isEqualTo(TestConfig.EXPECTED_TITLE);
        
        // Login with provided credentials
        loginPage.login(username, password);
        inventoryPage = new InventoryPage(driver);
        
        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
    }
    
    @Test(description = "Test adding individual items to cart")
    public void testAddIndividualItemsToCart() {
        // Navigate to login page
        driver.get(TestConfig.BASE_URL);
        loginPage = new LoginPage(driver);
        
        // Login with valid credentials
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        
        // Verify successful login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("inventory_item")));
        assertThat(inventoryPage.isInventoryPageDisplayed()).isTrue();
        
        // Add first item to cart
        inventoryPage.addItemToCart(0);
        String cartCount1 = inventoryPage.getCartItemCount();
        assertThat(cartCount1).isEqualTo("1");
        
        // Add second item to cart
        inventoryPage.addItemToCart(1);
        String cartCount2 = inventoryPage.getCartItemCount();
        assertThat(cartCount2).isEqualTo("2");
    }
    
    @Test(description = "Test checkout process with validation")
    public void testCheckoutProcess() {
        // Navigate to login page
        driver.get(TestConfig.BASE_URL);
        loginPage = new LoginPage(driver);
        
        // Login with valid credentials
        loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD);
        inventoryPage = new InventoryPage(driver);
        
        // Add items to cart
        inventoryPage.addMultipleItemsToCart(2);
        
        // Go to cart
        inventoryPage.clickShoppingCart();
        cartPage = new CartPage(driver);
        
        // Verify cart page is displayed
        assertThat(cartPage.isCartPageDisplayed()).isTrue();
        assertThat(cartPage.getCartItemCount()).isEqualTo(2);
        
        // Proceed to checkout
        cartPage.clickCheckout();
        checkoutPage = new CheckoutPage(driver);
        
        // Fill checkout information
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("first-name")));
        checkoutPage.fillCheckoutInformation(
            TestConfig.FIRST_NAME, 
            TestConfig.LAST_NAME, 
            TestConfig.POSTAL_CODE
        );
        checkoutPage.clickContinue();
        
        // Verify checkout overview page
        wait.until(ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.id("finish")));
        
        // Complete order
        checkoutPage.clickFinish();
        
        // Verify order completion
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.className("complete-header")));
        assertThat(checkoutPage.isCheckoutCompletePageDisplayed()).isTrue();
        assertThat(checkoutPage.getCompleteHeaderText()).isEqualTo("Thank you for your order!");
    }
    
    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginCredentials() {
        return TestConfig.LOGIN_CREDENTIALS;
    }
} 