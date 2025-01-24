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
        if (fullName == null) {
            LoggerUtility.info("Customer is not selected selected");
        } else {
            webElementsMethods.select(OpenAccountLocators.selectCustomer, fullName);

            LoggerUtility.info("Customer is selected");
        }
    }

    public void selectCurrency(String currency) {
        if(currency == null) {
            LoggerUtility.info("Currency is not selected selected");
        } else {
            webElementsMethods.select(OpenAccountLocators.selectCurrency, currency);

            LoggerUtility.info("Currency is selected");
        }
    }

    public String clickOnProcessButton(String fullName, String currency) {
        StringBuilder alertMessages = new StringBuilder();
        String idToReturn = null;

        webElementsMethods.clickOn(OpenAccountLocators.processButton);

        LoggerUtility.info("Clicked on Process button");

        alertMessages.append(webElementsMethods.getAlertTextForEmptyElement(fullName, OpenAccountLocators.selectCustomer));
        alertMessages.append(webElementsMethods.getAlertTextForEmptyElement(currency, OpenAccountLocators.selectCurrency));

        if (alertMessages.toString().isEmpty()) {
            alertMessages.append(alertsMethods.getAlertsTextAndAccept());
        }

        String actualSuccessMessage = alertMessages.toString();

        if(actualSuccessMessage.contains("select")) {
            Assert.assertTrue(actualSuccessMessage.equals("Please select an item in the list."));

            idToReturn = "";
            LoggerUtility.info("Warning alert message is displayed");
        }

        else{
            idToReturn = actualSuccessMessage.split(":")[1];

            LoggerUtility.info("Accepted pop-up alert");

            Assert.assertTrue(actualSuccessMessage.contains("Account created successfully with account Number"));

            LoggerUtility.info("Account is created successfully");
        }

        return idToReturn;
    }
}
