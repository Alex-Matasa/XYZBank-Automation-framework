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

public class WithdrawPage extends BasePage {

    public WithdrawPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//label[text()='Amount to be Withdrawn :']/parent::div/input")
    private WebElement amount;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement withdrawSubmitButton;
    @FindBy(xpath ="//span[@ng-show='message']")
    private WebElement message;

    public void withdraw(Transactions transactions, Customers customers, Accounts accounts) {
        amount.sendKeys(transactions.getAmount());
        LoggerUtility.info("Entered amount of money to withdraw");

        if (Integer.parseInt(transactions.getAmount()) > 0) {
            accounts.setBalance(String.valueOf(Integer.parseInt(accounts.getBalance())
                    - Integer.parseInt(transactions.getAmount())));
        }

        webElementsMethods.clickOn(withdrawSubmitButton);
        LoggerUtility.info("Clicked on Withdraw submitting button");

        Assert.assertTrue(assertionsMethods.validateText(message, "Transaction successful"));
        LoggerUtility.info("Successful message is displayed");

    }






}
