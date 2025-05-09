package pageObjects;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pageObjects.locators.CustomerLoginLocators;

public class CustomerLoginPage extends BasePage{

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    public void selectName(String fullName) {
        webElementsMethods.selectByText(CustomerLoginLocators.selectName, fullName);

        LoggerUtility.info("Name is selected");
    }

    public void clickOnLoginButton() {
        webElementsMethods.clickOn(CustomerLoginLocators.loginButton);

        LoggerUtility.info("Clicked on Login button");
    }

    public boolean  validateCustomerLoginDashboard() {
        return webElementsMethods.isElementVisible(CustomerLoginLocators.selectName);
    }
}
