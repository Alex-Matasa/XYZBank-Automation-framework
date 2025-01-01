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

    public void leaveEmptyField(String firstName, String lastName, String postCode) {
        String alertMessage = null;

        if (firstName == null) {
            LoggerUtility.info("Left the First Name field empty");
            enterLastName(lastName);
            enterPostCode(postCode);
            webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
            LoggerUtility.info("Clicked on Add Customer Submit button");

            WebElement firstNameField = driver.findElement(AddCustomerLocators.fName);
            alertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", firstNameField);
        } else if (lastName == null) {
            enterFirstName(firstName);
            LoggerUtility.info("Left the Last Name field empty");
            enterPostCode(postCode);
            webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
            LoggerUtility.info("Clicked on Add Customer Submit button");

            WebElement lastNameField = driver.findElement(AddCustomerLocators.lName);
            alertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", lastNameField);
        } else {
            enterFirstName(firstName);
            enterLastName(lastName);
            LoggerUtility.info("Left the Post Code field empty");
            webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
            LoggerUtility.info("Clicked on Add Customer Submit button");

            WebElement postCodeField = driver.findElement(AddCustomerLocators.postCode);
            alertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", postCodeField);
        }

        Assert.assertTrue(assertionsMethods.validateText(alertMessage, "Please fill out this field."));
        LoggerUtility.info("Warning alert message is displayed");
    }

    public void enterFirstName(String firstName) {
        webElementsMethods.sendKeys(AddCustomerLocators.fName, firstName);
        LoggerUtility.info("Entered First Name");
    }

    public void enterLastName(String lastName) {
        webElementsMethods.sendKeys(AddCustomerLocators.lName, lastName);
        LoggerUtility.info("Entered Last Name");
    }

    public void enterPostCode(String postCode) {
        webElementsMethods.sendKeys(AddCustomerLocators.postCode, postCode);
        LoggerUtility.info("Entered Post Code");
    }

    public String clickOnSubmitButton() {
        webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        String actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");

        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Customer added successfully with customer id"));
        LoggerUtility.info("Validated successful message");

        return actualSuccessMessage.split(":")[1];
    }


}
