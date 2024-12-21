package pageObjects.customer;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.Transactions;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.WithdrawLocators;

public class WithdrawPage extends BasePage {

    public WithdrawPage(WebDriver driver) {
        super(driver);
    }

    public void withdraw(Transactions transactions, Customers customers, Accounts accounts) {

        webElementsMethods.sendKeys(WithdrawLocators.amount, transactions.getAmount());
        LoggerUtility.info("Entered amount of money to withdraw");

        if (Integer.parseInt(transactions.getAmount()) > 0) {
            accounts.setBalance(String.valueOf(Integer.parseInt(accounts.getBalance())
                    - Integer.parseInt(transactions.getAmount())));
        }
        webElementsMethods.clickOn(WithdrawLocators.withdrawSubmitButton);
        LoggerUtility.info("Clicked on Withdraw submitting button");

        Assert.assertTrue(assertionsMethods.validateText(WithdrawLocators.message, "Transaction successful"));
        LoggerUtility.info("Successful message is displayed");

    }






}
