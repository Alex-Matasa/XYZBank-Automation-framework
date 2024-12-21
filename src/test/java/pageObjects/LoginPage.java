package pageObjects;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.locators.LoginLocators;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnBankManagerLogin() {
        webElementsMethods.clickOn(LoginLocators.bankManagerLoginButton);
        LoggerUtility.info("Clicked on Bank Manager Login button");
    }
    public void clickOnCustomerLogin() {
        webElementsMethods.clickOn(LoginLocators.customerLoginButton);
        LoggerUtility.info("Clicked on Customer Login button");
    }




}
