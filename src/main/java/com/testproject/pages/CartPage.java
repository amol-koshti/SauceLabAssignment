package com.testproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    
    private WebDriver driver;
    
    @FindBy(id = "checkout")
    private WebElement checkoutButton;
    
    @FindBy(className = "cart_item")
    private java.util.List<WebElement> cartItems;
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void clickCheckout() {
        checkoutButton.click();
    }
    
    public int getCartItemCount() {
        return cartItems.size();
    }
    
    public boolean isCartPageDisplayed() {
        return checkoutButton.isDisplayed();
    }
} 