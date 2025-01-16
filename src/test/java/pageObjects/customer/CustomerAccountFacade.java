package pageObjects.customer;

import dataObjects.Customers;
import helperMethods.UtilityMethods;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.CustomerAccountFacadeLocators;

import java.util.List;

public class CustomerAccountFacade extends BasePage {

    public CustomerAccountFacade(WebDriver driver) {
        super(driver);
    }

    private TransactionsPage transactionsPage;

    public void navigateToPage(String pageName) {

        switch (pageName) {
            case "Transactions" :
                transactionsPage = new TransactionsPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.transactionsButton);
                LoggerUtility.info("Clicked on Transaction tab");
                break;
            case "Deposit" :
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.depositButton);
                LoggerUtility.info("Clicked on Deposit tab");
                break;
            case "Withdraw" :
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.withdrawlButton);
                LoggerUtility.info("Clicked on Withdrawl tab");
                break;
        }
    }

    public void selectAccountId(String id) {
        webElementsMethods.select(CustomerAccountFacadeLocators.selectAccountId, id);
        LoggerUtility.info("The account is selected");
    }

    public void enterAmount(String amount) {
        if(amount != null) {
            webElementsMethods.sendKeys(CustomerAccountFacadeLocators.amount, amount);
            LoggerUtility.info(amount + " entered to deposit");
        }

    }

    public void submitTransaction(String amount) {
        webElementsMethods.clickOn(CustomerAccountFacadeLocators.submitTransactionButton);
        LoggerUtility.info("Clicked on deposit button");

        if (amount == null) {
            WebElement elementField = driver.findElement(CustomerAccountFacadeLocators.amount);
            Assert.assertTrue(assertionsMethods.validateText((String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", elementField), "Please fill out this field."));

            LoggerUtility.info("Warning alert message was displayed.");
        } else if (UtilityMethods.parseStringToInt(amount) >= 0) {
            Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.message, "Deposit Successful"));

            LoggerUtility.info("Successful alert message was displayed");
        }
    }

    public void validateWelcomingNoAccount(Customers customers) {
        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.welcome, customers.getFullName()));
        Assert.assertTrue(assertionsMethods.validatePartialText(CustomerAccountFacadeLocators.pleaseOpenAccountMessage, "open an account"));
        LoggerUtility.info("Validated successful message");
    }

    public List<String> getActualAccountInfo() {
        return webElementsMethods.getDataAsStringList(CustomerAccountFacadeLocators.accountInfoDisplayedList);
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




























}


