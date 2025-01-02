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

        if (firstName == null && lastName == null && postCode == null) {
            LoggerUtility.info("Left all the fields empty");
            webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
            LoggerUtility.info("Clicked on Add Customer Submit button");

            WebElement firstNameField = driver.findElement(AddCustomerLocators.fName);
            alertMessage = (String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", firstNameField);
        } else if (firstName == null) {
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
        String textToReturn = null;

        webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        String actualMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");

        if(actualMessage.startsWith("Customer")) {
            Assert.assertTrue(assertionsMethods.validatePartialText(actualMessage, "Customer added successfully with customer id"));
            LoggerUtility.info("Validated successful message");
            textToReturn = actualMessage.split(":")[1];
        }
        else {
            Assert.assertTrue(assertionsMethods.validateText(actualMessage, "Please check the details. Customer may be duplicate."));
            LoggerUtility.info("Warning alert message is displayed");
        }

        return textToReturn;
    }


}
