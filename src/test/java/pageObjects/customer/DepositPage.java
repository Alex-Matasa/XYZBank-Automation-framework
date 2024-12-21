package pageObjects.customer;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.Transactions;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.DepositLocators;

public class DepositPage extends BasePage {

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    public void deposit(Transactions transactions, Customers customers, Accounts accounts) {
        webElementsMethods.sendKeys(DepositLocators.amount,transactions.getAmount());
        LoggerUtility.info("Entered amount of money to deposit");

        if (Integer.parseInt(transactions.getAmount()) > 0) {
            accounts.setBalance(String.valueOf(Integer.parseInt(accounts.getBalance())
                    + Integer.parseInt(transactions.getAmount())));
        }

        webElementsMethods.clickOn(DepositLocators.depositSubmitButton);
        LoggerUtility.info("Clicked on Deposit submitting button");

        Assert.assertTrue(assertionsMethods.validateText(DepositLocators.message, "Deposit Successful"));
        LoggerUtility.info("Successful message is displayed");
    }








}

