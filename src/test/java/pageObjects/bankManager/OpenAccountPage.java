package pageObjects.bankManager;

import dataObjects.AccountData;
import dataObjects.CustomerData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

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

    public void clickOnProcessButton(CustomerData customerData) {
        webElementsMethods.clickOn(processButton);
        LoggerUtility.info("Clicked on Process button ");
        actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");

        AccountData newAccount = new AccountData();
        customerData.getAccounts().add(newAccount);
        customerData.getAccounts().get(0).setAccountId(actualSuccessMessage.split(":")[1]);
        customerData.getAccounts().get(0).setBalance("0");
    }

    public void openNewAccount(AccountData accountData, CustomerData customerData){
        selectCustomer(customerData.getFullName());
        selectCurrency(accountData.getCurrency());
        clickOnProcessButton(customerData);
        validateAccountIsOpened();
    }

    public void validateAccountIsOpened() {
        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Account created successfully with account Number"));
        LoggerUtility.info("Account is created successfully");
    }


}
