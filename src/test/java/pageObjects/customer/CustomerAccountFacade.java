package pageObjects.customer;

import dataObjects.Customers;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.CustomerAccountFacadeLocators;
import pageObjects.locators.DepositLocators;
import pageObjects.locators.WithdrawLocators;

import java.util.List;
import java.util.Objects;

public class CustomerAccountFacade extends BasePage {

    public CustomerAccountFacade(WebDriver driver) {
        super(driver);
    }

    private TransactionsPage transactionsPage;

    public void navigateToPage(String pageName) {

        switch (pageName) {
            case "Transactions":
                transactionsPage = new TransactionsPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.transactionsButton);
                LoggerUtility.info("Clicked on Transaction tab");
                break;
            case "Deposit":
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.depositButton);
                LoggerUtility.info("Clicked on Deposit tab");
                break;
            case "Withdraw":
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.withdrawlButton);
                LoggerUtility.info("Clicked on Withdrawl tab");
                break;
        }
    }

    public void selectAccountId(String id) {
        webElementsMethods.select(CustomerAccountFacadeLocators.selectAccountId, id);

        LoggerUtility.info("The account is selected");
    }

    public void enterAmount(String amount, String type) {
        if (amount != null) {
            if (type.equals("Credit")) webElementsMethods.sendKeys(DepositLocators.amountToBeDeposited, amount);
            else webElementsMethods.sendKeys(WithdrawLocators.amountToBeWithdrawn, amount);
        }
    }

    public void submitTransaction(String amount, String type) {

        if (type.equals("Credit")) webElementsMethods.clickOn(DepositLocators.submitDepositButton);
        else webElementsMethods.clickOn(WithdrawLocators.submitWithdrawButton);

        String message;

        LoggerUtility.info("Clicked on submit transaction button");

        if (amount == null) {
            WebElement elementField = driver.findElement(CustomerAccountFacadeLocators.amount);
            message = (String) Objects.requireNonNull(((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", elementField));
//            Assert.assertEquals((String) Objects.requireNonNull(((JavascriptExecutor) driver).executeScript(
//                    "return arguments[0].validationMessage;", elementField)), "Please fill out this field.");
//
//            LoggerUtility.info("Warning alert message was displayed.");

        } else if (Integer.parseInt(amount) > 0) {
            switch (type) {
                case "Credit":
                    Assert.assertTrue(assertionsMethods.actualEqualExpected(CustomerAccountFacadeLocators.message, "Deposit Successful"));
                    break;
                case "Debit":
                    Assert.assertFalse(assertionsMethods.actualEqualExpected(CustomerAccountFacadeLocators.message, "Transaction Successful"));
                    break;
            }
        }
        LoggerUtility.info("Successful alert message was displayed");
    }

    public List<String> getActualAccountInfo() {
        return webElementsMethods.extractDataAsStringList(CustomerAccountFacadeLocators.accountInfoDisplayedList);
    }

    public boolean validateCustomerHasNoAccount(Customers customer) {
        Assert.assertTrue(assertionsMethods.actualEqualExpected(CustomerAccountFacadeLocators.welcome, customer.getFullName()));
        boolean hasNoAccount = assertionsMethods.actualContainsExpected(CustomerAccountFacadeLocators.pleaseOpenAccountMessage, "open an account");
        if(hasNoAccount) LoggerUtility.info("Validated successful message");

        return hasNoAccount;
    }

    public void logout() {
        webElementsMethods.clickOn(CustomerAccountFacadeLocators.logoutButton);
        LoggerUtility.info("Clicked on logout button");

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Customer logged out");
    }
}


