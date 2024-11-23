package pageObjects.bankManager;

import dataObjects.CustomerData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

import java.util.List;

public class AddCustomerPage extends BasePage {

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form/div/input")
    private List<WebElement> formList;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCustomerSubmit;
    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lName;
    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement fName;
    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCode;


    private String actualSuccessMessage;

    public void fillHalfForm() {
        fName.sendKeys("Customer2");
        addCustomerSubmit.click();
        String errorMessage = lName.getAttribute("validationMessage");
        System.out.println(errorMessage);
    }

    public void enterFirstName(CustomerData customerData) {
        fName.sendKeys(customerData.getFirstName());
        LoggerUtility.info("Entered First Name");
    }

    public void enterLastName(CustomerData customerData) {
        lName.sendKeys(customerData.getLastName());
        LoggerUtility.info("Entered Last Name");
    }

    public void enterPostCode(CustomerData customerData) {
        postCode.sendKeys(customerData.getPostCode());
        LoggerUtility.info("Entered Post Code");
    }

    public void fillEntireFormAndSubmit(CustomerData customerData) {
        enterFirstName(customerData);
        enterLastName(customerData);
        enterPostCode(customerData);
        clickOnSubmitButton(customerData);
    }




    /////   helper methods  /////

    private void clickOnSubmitButton(CustomerData customerData) {
        webElementsMethods.clickOn(addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");
        customerData.setCustomerId(actualSuccessMessage.split(":")[1]);

        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Customer added successfully with customer id"));
        LoggerUtility.info("Validated successful message");
    }




}
