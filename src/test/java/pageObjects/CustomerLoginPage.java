package pageObjects;

import dataObjects.Customers;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.locators.CustomerLoginLocators;

public class CustomerLoginPage extends BasePage{

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    public void selectName(String fullName) {
        webElementsMethods.select(CustomerLoginLocators.selectName, fullName);
        LoggerUtility.info("Name is selected");
    }

    public void clickOnLoginButton() {
        webElementsMethods.clickOn(CustomerLoginLocators.loginButton);
        LoggerUtility.info("Clicked on Login button");
    }

}
