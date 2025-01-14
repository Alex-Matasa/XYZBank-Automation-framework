package pageObjects.customer;

import dataObjects.Customers;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
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

    private TransactionsPage transactionsPage;
    private DepositPage depositPage;
    private WithdrawPage withdrawPage;

    public void navigateToPage(String pageName) {

        switch (pageName) {
            case "Transactions" :
                transactionsPage = new TransactionsPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.transactionsButton);
                LoggerUtility.info("Clicked on Transaction tab");
                break;
            case "Deposit" :
                depositPage = new DepositPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.depositButton);
                LoggerUtility.info("Clicked on Deposit tab");
                break;
            case "Withdraw" :
                withdrawPage = new WithdrawPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.withdrawlButton);
                LoggerUtility.info("Clicked on Withdrawl tab");
                break;
        }
    }

    public void selectAccountId(String id) {
        webElementsMethods.select(CustomerAccountFacadeLocators.selectAccountId, id);
        LoggerUtility.info("The account is selected");
    }

    public void validateWelcomingNoAccount(Customers customers) {
        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.welcome, customers.getFullName()));
        Assert.assertTrue(assertionsMethods.validatePartialText(CustomerAccountFacadeLocators.pleaseOpenAccountMessage, "open an account"));
        LoggerUtility.info("Validated successful message");
    }

    public List<String> getActualAccountInfo() {
        return webElementsMethods.getAStringList(CustomerAccountFacadeLocators.accountInfoDisplayedList);
    }




//    public void validateTransactionHistory(Customers customers) {
//        navigateToPage(this.transactions);
//        Accounts accounts = customers.getAccounts().get(0);
//        Transactions transactions = accounts.getTransactions().get(0);
//
//        Assert.assertTrue(transactionsPage.validateDepositHistory(transactions));
//        LoggerUtility.info("Validated deposit history");
//
//        Assert.assertTrue(transactionsPage.validateWithdrawHistory(transactions));
//        LoggerUtility.info("Validated withdraw history");
//    }

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


