package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.AddCustomerLocators;
import validation.ActualMessages;

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

    public void clickOnSubmitButton(String firstName, String lastName, String postCode) {
        webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        String message = "";

        if (firstName == null) {
            message = webElementsMethods.getAlertTextForEmptyElement(AddCustomerLocators.fName);
        } else if (lastName == null) {
            message = webElementsMethods.getAlertTextForEmptyElement(AddCustomerLocators.lName);
        } else if (postCode == null) {
            message = webElementsMethods.getAlertTextForEmptyElement(AddCustomerLocators.postCode);
        }

        else {
            message = alertsMethods.getAlertsTextAndAccept();
            LoggerUtility.info("Accepted pop-up alert");
        }
        ActualMessages.setActualMessage(message);
    }
}
