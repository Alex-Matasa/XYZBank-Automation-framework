package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.AddCustomerLocators;

public class AddCustomerPage extends BasePage {

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        if (firstName != null) {
            webElementsMethods.sendKeys(AddCustomerLocators.fName, firstName);
            LoggerUtility.info("Entered First Name");
        } else LoggerUtility.info("Left the First Name field empty");
    }

    public void enterLastName(String lastName) {
        if (lastName != null) {
            webElementsMethods.sendKeys(AddCustomerLocators.lName, lastName);
            LoggerUtility.info("Entered Last Name");
        } else LoggerUtility.info("Left the Last Name field empty");

    }

    public void enterPostCode(String postCode) {
        if (postCode != null) {
            webElementsMethods.sendKeys(AddCustomerLocators.postCode, postCode);
            LoggerUtility.info("Entered Post Code");
        } else LoggerUtility.info("Left the Post Code field empty");
    }

    public String clickOnSubmitButton(String firstName, String lastName, String postCode) {
        String customerIdToReturn = null;
        StringBuilder alertMessages = new StringBuilder();

        webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        alertMessages.append(getAlertTextForEmptyElement(firstName, AddCustomerLocators.fName));
        alertMessages.append(getAlertTextForEmptyElement(lastName, AddCustomerLocators.lName));
        alertMessages.append(getAlertTextForEmptyElement(postCode, AddCustomerLocators.postCode));

        if (alertMessages.toString().isEmpty()) {
            alertMessages.append(alertsMethods.getAlertsTextAndAccept());
        }

        String actualAlertMessage = alertMessages.toString();
        if (actualAlertMessage.contains("id")) {
            Assert.assertTrue(assertionsMethods.actualContainsExpected(actualAlertMessage, "Customer added successfully with customer id"));
            LoggerUtility.info("Validated successful message");
            LoggerUtility.info("Accepted pop-up alert");
            customerIdToReturn = actualAlertMessage.split(":")[1];
        } else if (actualAlertMessage.contains("field")) {
            Assert.assertTrue(actualAlertMessage.contains("Please fill out this field."));
            LoggerUtility.info("Warning alert message is displayed");
        } else {
            Assert.assertTrue(assertionsMethods.actualEqualExpected(actualAlertMessage, "Please check the details. Customer may be duplicate."));
            LoggerUtility.info("Warning alert message is displayed");
            LoggerUtility.info("Accepted pop-up alert");
        }

        return customerIdToReturn;
    }

    private String getAlertTextForEmptyElement(String value, By locator) {
        if (value == null) {
            WebElement elementField = driver.findElement(locator);
            return (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", elementField);
        } else {
            return "";
        }
    }

}
