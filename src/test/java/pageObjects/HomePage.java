package pageObjects;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;

import pageObjects.locators.CommonLocators;
import pageObjects.locators.LoginLocators;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
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

    public void clickOnHomePageButton() {
        webElementsMethods.clickOn(CommonLocators.homeButton);
        LoggerUtility.info("Navigated to Home Page");
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Navigated to Home Page");
    }
}
