package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.OpenAccountLocators;
import validation.ActualMessages;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public void selectCustomer(String fullName) {
        if (fullName == null) {
            LoggerUtility.info("Customer is not selected");
        } else {
            webElementsMethods.selectByText(OpenAccountLocators.selectCustomer, fullName);
            LoggerUtility.info("Customer is selected");
        }
    }

    public void selectCurrency(String currency) {
        if(currency == null) {
            LoggerUtility.info("Currency is not selected");
        } else {
            webElementsMethods.selectByText(OpenAccountLocators.selectCurrency, currency);
            LoggerUtility.info("Currency is selected");
        }
    }

    public String clickOnProcessButton(String fullName, String currency) {
        webElementsMethods.clickOn(OpenAccountLocators.processButton);
        LoggerUtility.info("Clicked on Process button");

        String message = "";
        String accountNumber = "";

        if (fullName == null) {
            message = webElementsMethods.getAlertTextForEmptyElement(OpenAccountLocators.selectCustomer);
        } else if (currency == null) {
            message = webElementsMethods.getAlertTextForEmptyElement(OpenAccountLocators.selectCurrency);
        } else {
            message = alertsMethods.getAlertsTextAndAccept();
            accountNumber = message.split(":")[1].trim();
        }

        ActualMessages.setActualMessage(message);

        return accountNumber;
    }
}
