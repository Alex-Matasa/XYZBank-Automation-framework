package pageObjects;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;

import pageObjects.locators.LoginLocators;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnBankManagerLogin() {
        webElementsMethods.clickOn(LoginLocators.bankManagerLoginButton);

        LoggerUtility.info("Clicked on Bank Manager Login button");

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Manager logged in");
    }
    public void clickOnCustomerLogin() {
        webElementsMethods.clickOn(LoginLocators.customerLoginButton);

        LoggerUtility.info("Clicked on Customer Login button");

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Customer logged in");
    }
}
