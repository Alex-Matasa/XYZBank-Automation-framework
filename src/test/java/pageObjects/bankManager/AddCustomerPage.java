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

    public void fillHalfForm() {
        fName.sendKeys("Customer2");
        addCustomerSubmit.click();
        String errorMessage = lName.getAttribute("validationMessage");
        System.out.println(errorMessage);
    }

    public void enterFirstName(String firstName) {
        fName.sendKeys(firstName);
        LoggerUtility.info("Entered First Name");
    }

    public void enterLastName(String lastName) {
        lName.sendKeys(lastName);
        LoggerUtility.info("Entered Last Name");
    }

    public void enterPostCode(String postCode) {
        this.postCode.sendKeys(postCode);
        LoggerUtility.info("Entered Post Code");
    }

    public String clickOnSubmitButton() {
        webElementsMethods.clickOn(addCustomerSubmit);
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
