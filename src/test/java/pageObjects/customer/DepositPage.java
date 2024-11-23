package pageObjects.customer;

import dataObjects.CustomerAccountData;
import dataObjects.CustomerData;
import dataObjects.TransactionsData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

public class DepositPage extends BasePage {

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[text()='Amount to be Deposited :']/parent::div/input")
    private WebElement amount;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement depositSubmitButton;
    @FindBy(xpath ="//span[@ng-show='message']")
    private WebElement message;

    public void deposit(TransactionsData transactionsData, CustomerData customerData, CustomerAccountData customerAccountData) {
        amount.sendKeys(transactionsData.getAmount());
        LoggerUtility.info("Entered amount of money to deposit");

        if (Integer.parseInt(transactionsData.getAmount()) > 0) {
            customerAccountData.setBalance(String.valueOf(Integer.parseInt(customerAccountData.getBalance())
                    + Integer.parseInt(transactionsData.getAmount())));
        }

        webElementsMethods.clickOn(depositSubmitButton);
        LoggerUtility.info("Clicked on Deposit submitting button");

        Assert.assertTrue(assertionsMethods.validateText(message, "Deposit Successful"));
        LoggerUtility.info("Successful message is displayed");

    }








}

