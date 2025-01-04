package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
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
        String actualAlertMessage = null;

        webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        if (firstName == null) {
            WebElement firstNameField = driver.findElement(AddCustomerLocators.fName);
            actualAlertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", firstNameField);
        } else if (lastName == null) {
            WebElement lastNameField = driver.findElement(AddCustomerLocators.lName);
            actualAlertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", lastNameField);
        } else if (postCode == null) {
            WebElement postCodeField = driver.findElement(AddCustomerLocators.postCode);
            actualAlertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", postCodeField);
        } else {
            actualAlertMessage = alertsMethods.getAlertsTextAndAccept();
        }

        if (actualAlertMessage.contains("id")) {
            Assert.assertTrue(assertionsMethods.validatePartialText(actualAlertMessage, "Customer added successfully with customer id"));
            LoggerUtility.info("Validated successful message");
            LoggerUtility.info("Accepted pop-up alert");
            customerIdToReturn = actualAlertMessage.split(":")[1];
        } else if (actualAlertMessage.contains("field")) {
            Assert.assertTrue(assertionsMethods.validateText(actualAlertMessage, "Please fill out this field."));
            LoggerUtility.info("Warning alert message is displayed");
        } else {
            Assert.assertTrue(assertionsMethods.validateText(actualAlertMessage, "Please check the details. Customer may be duplicate."));
            LoggerUtility.info("Warning alert message is displayed");
            LoggerUtility.info("Accepted pop-up alert");
        }

        return customerIdToReturn;
    }


}
