package com.testproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    
    private WebDriver driver;
    
    @FindBy(id = "first-name")
    private WebElement firstNameField;
    
    @FindBy(id = "last-name")
    private WebElement lastNameField;
    
    @FindBy(id = "postal-code")
    private WebElement postalCodeField;
    
    @FindBy(id = "continue")
    private WebElement continueButton;
    
    @FindBy(id = "finish")
    private WebElement finishButton;
    
    @FindBy(className = "complete-header")
    private WebElement completeHeader;
    
    @FindBy(className = "complete-text")
    private WebElement completeText;
    
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    
    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }
    
    public void enterPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }
    
    public void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }
    
    public void clickContinue() {
        continueButton.click();
    }
    
    public void clickFinish() {
        finishButton.click();
    }
    
    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }
    
    public String getCompleteText() {
        return completeText.getText();
    }
    
    public boolean isCheckoutCompletePageDisplayed() {
        return completeHeader.isDisplayed();
    }
} 