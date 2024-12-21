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
import pageObjects.locators.CustomerAccountFacadeLocators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerAccountFacade extends BasePage {

    public CustomerAccountFacade(WebDriver driver) {
        super(driver);
    }

    private final  String transactions = "Transactions";
    private final  String deposit = "Deposit";
    private final  String withdraw = "Withdraw";

    private TransactionsPage transactionsPage;
    private DepositPage depositPage;
    private WithdrawPage withdrawPage;

    public void validateAccountInfo(Customers customers){
        Accounts accounts = customers.getAccounts().get(0);
        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.welcome, customers.getFullName()));
        List<String> customerAccountInfo = List.of(accounts.getAccountId(), accounts.getBalance(), accounts.getCurrency());
        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.accountInfoDisplayedList, customerAccountInfo));
        LoggerUtility.info("Correct account info are displayed");
    }

    public void validateWelcomingNoAccount(Customers customers) {
        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.welcome, customers.getFullName()));
        Assert.assertTrue(assertionsMethods.validatePartialText(CustomerAccountFacadeLocators.openAccountMessage, "open an account"));
        LoggerUtility.info("Validated successful message");
    }

    public void navigateToPage(String pageName) {

        switch (pageName) {
            case "Transactions" :
                transactionsPage = new TransactionsPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.transactionsButton);
                LoggerUtility.info("Clicked on Transactions button");
                break;
            case "Deposit" :
                depositPage = new DepositPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.depositButton);
                LoggerUtility.info("Clicked on Deposit button");
                break;
            case "Withdraw" :
                withdrawPage = new WithdrawPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.withdrawlButton);
                LoggerUtility.info("Clicked on Withdrawl button");
                break;
        }
    }



    public void withdrawMoney(Customers customers) {
        navigateToPage(withdraw);
        Accounts accounts = customers.getAccounts().get(0);
        Transactions transactions = accounts.getTransactions().get(0);
        withdrawPage.withdraw(transactions, customers, accounts);
        List <String> info = List.of(getDateAndTime(), transactions.getAmount(), "Debit");
        transactions.setWithdrawHistory(info);
        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.balanceInfo, accounts.getBalance()));
        LoggerUtility.info("Balance is correctly updated");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void validateTransactionHistory(Customers customers) {
        navigateToPage(this.transactions);
        Accounts accounts = customers.getAccounts().get(0);
        Transactions transactions = accounts.getTransactions().get(0);

        Assert.assertTrue(transactionsPage.validateDepositHistory(transactions));
        LoggerUtility.info("Validated deposit history");

        Assert.assertTrue(transactionsPage.validateWithdrawHistory(transactions));
        LoggerUtility.info("Validated withdraw history");
    }

    public void logout() {
        webElementsMethods.clickOn(CustomerAccountFacadeLocators.logoutButton);
        LoggerUtility.info("Clicked on logout button");
    }

    /////   helper methods ////

    public String getDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(formatter);

    }


























}


