package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.AddCustomerLocators;

import java.util.List;

public class AddCustomerPage extends BasePage {

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }




    public void fillHalfForm() {
        webElementsMethods.sendKeys(AddCustomerLocators.fName, "Customer2");
        webElementsMethods.clickOn(AddCustomerLocators.addCustomerSubmit);

        String errorMessage = driver.findElement(AddCustomerLocators.lName).getDomAttribute("validationMessage");
        System.out.println(errorMessage);
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

//    public void fillEntireFormAndSubmit(CustomerData customerData) {
//        enterFirstName(customerData);
//        enterLastName(customerData);
//        enterPostCode(customerData);
//        clickOnSubmitButton(customerData);
//    }




}
