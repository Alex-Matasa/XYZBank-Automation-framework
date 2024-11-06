package pageObjects.bankManager;

import dataObjects.AddCustomerData;
import dataObjects.CustomerAccountData;
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

    public void enterFirstName(AddCustomerData addCustomerData) {
        fName.sendKeys(addCustomerData.getFirstName());
        LoggerUtility.info("Entered First Name");
    }

    public void enterLastName(AddCustomerData addCustomerData) {
        lName.sendKeys(addCustomerData.getLastName());
        LoggerUtility.info("Entered Last Name");
    }

    public void enterPostCode(AddCustomerData addCustomerData) {
        postCode.sendKeys(addCustomerData.getPostCode());
        LoggerUtility.info("Entered Post Code");
    }

    public void fillEntireFormAndSubmit(AddCustomerData addCustomerData, CustomerAccountData customerAccountData) {
        enterFirstName(addCustomerData);
        enterLastName(addCustomerData);
        customerAccountData.setFullName(addCustomerData.getFirstName() + " " + addCustomerData.getLastName());
        enterPostCode(addCustomerData);
        clickOnAddCustomerSubmissionButton(customerAccountData);
        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Customer added successfully with customer id"));
        LoggerUtility.info("Customer is added successfully");
    }


    /////   helper methods  /////

    private void clickOnAddCustomerSubmissionButton(CustomerAccountData customerAccountData) {
        webElementsMethods.clickOn(addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");
        actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");
        customerAccountData.setCustomerId(actualSuccessMessage.split(":")[1]);
    }


}
