package pageObjects.bankManager;


import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.OpenAccountLocators;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void selectCustomer(String fullName) {
        webElementsMethods.select(OpenAccountLocators.selectCustomer, fullName);
        LoggerUtility.info("Customer is selected");
    }

    public void selectCurrency(String currency) {
        webElementsMethods.select(OpenAccountLocators.selectCurrency, currency);
        LoggerUtility.info("Currency is selected");
    }

    public String clickOnProcessButton() {
        webElementsMethods.clickOn(OpenAccountLocators.processButton);
        LoggerUtility.info("Clicked on Process button ");
        String actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");

        Assert.assertTrue(assertionsMethods.actualContainsExpected(actualSuccessMessage, "Account created successfully with account Number"));
        LoggerUtility.info("Account is created successfully");

        return actualSuccessMessage.split(":")[1];
    }


}
