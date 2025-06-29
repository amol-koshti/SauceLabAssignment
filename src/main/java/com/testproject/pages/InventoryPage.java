package com.testproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    
    private WebDriver driver;
    
    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;
    
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;
    
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void addItemToCart(int itemIndex) {
        if (itemIndex < inventoryItems.size()) {
            WebElement addToCartButton = inventoryItems.get(itemIndex)
                .findElement(org.openqa.selenium.By.cssSelector("button[data-test^='add-to-cart']"));
            addToCartButton.click();
        }
    }
    
    public void addMultipleItemsToCart(int numberOfItems) {
        for (int i = 0; i < numberOfItems && i < inventoryItems.size(); i++) {
            addItemToCart(i);
        }
    }
    
    public void clickShoppingCart() {
        shoppingCartLink.click();
    }
    
    public String getCartItemCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public boolean isInventoryPageDisplayed() {
        return !inventoryItems.isEmpty();
    }
    
    public List<String> getItemNames() {
        return itemNames.stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());
    }
} 