package pageObjects.bankManager;

import dataObjects.CustomerAccountData;
import dataObjects.CustomerData;
import dataObjects.InputCustomerData;
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

    public void enterFirstName(InputCustomerData inputCustomerData) {
        fName.sendKeys(inputCustomerData.getFirstName());
        LoggerUtility.info("Entered First Name");
    }

    public void enterLastName(InputCustomerData inputCustomerData) {
        lName.sendKeys(inputCustomerData.getLastName());
        LoggerUtility.info("Entered Last Name");
    }

    public void enterPostCode(InputCustomerData inputCustomerData) {
        postCode.sendKeys(inputCustomerData.getPostCode());
        LoggerUtility.info("Entered Post Code");
    }

    public void fillEntireFormAndSubmit(InputCustomerData inputCustomerData, CustomerData customerData) {
        enterFirstName(inputCustomerData);
        enterLastName(inputCustomerData);
        enterPostCode(inputCustomerData);
        clickOnAddCustomerSubmissionButton(inputCustomerData, customerData);
        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Customer added successfully with customer id"));
        LoggerUtility.info("Customer is added successfully");
    }


    /////   helper methods  /////

    private void clickOnAddCustomerSubmissionButton(InputCustomerData inputCustomerData, CustomerData customerData) {
        webElementsMethods.clickOn(addCustomerSubmit);
        LoggerUtility.info("Clicked on Add Customer Submit button");

        customerData.setFirstName(inputCustomerData.getFirstName());
        customerData.setLastName(inputCustomerData.getLastName());
        customerData.setPostCode(inputCustomerData.getPostCode());
        customerData.setFullName(inputCustomerData.getFirstName() + " " + inputCustomerData.getLastName());

        actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");
        customerData.setCustomerId(actualSuccessMessage.split(":")[1]);
    }


}
