package pageObjects.bankManager;

import dataObjects.AddCustomerData;
import dataObjects.CustomerAccountData;
import dataObjects.OpenAccountData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

import java.util.List;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//select[@name='userSelect']")
    private WebElement selectCustomer;
    @FindBy(xpath = "//select[@name='currency']")
    private WebElement selectCurrency;
    @FindBy(xpath = "//button[text()='Process']")
    private WebElement processButton;

    private String actualSuccessMessage;


    public void selectCustomer(String fullName) {
        webElementsMethods.select(selectCustomer,  fullName);
        LoggerUtility.info("Customer is selected");
    }

    public void selectCurrency(String currency) {
        webElementsMethods.select(selectCurrency,currency);
        LoggerUtility.info("Currency is selected");
    }

    public void clickOnProcessButton(CustomerAccountData customerAccountData) {
        webElementsMethods.clickOn(processButton);
        LoggerUtility.info("Clicked on Process button ");
        actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");
        customerAccountData.setAccountId(actualSuccessMessage.split(":")[1]);
    }

    public void openNewAccount(OpenAccountData openAccountData, AddCustomerData addCustomerData, CustomerAccountData customerAccountData){
        selectCustomer(customerAccountData.getFullName());
        selectCurrency(openAccountData.getCurrency());
        customerAccountData.setCurrency(openAccountData.getCurrency());
        clickOnProcessButton(customerAccountData);
        customerAccountData.setBalance("0");
        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Account created successfully with account Number"));
        LoggerUtility.info("Account is created successfully");
    }


}
