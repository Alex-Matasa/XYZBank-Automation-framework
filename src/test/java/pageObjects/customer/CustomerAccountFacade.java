package pageObjects.customer;


import dataObjects.AccountData;
import dataObjects.CustomerData;
import dataObjects.TransactionsData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerAccountFacade extends BasePage {

    public CustomerAccountFacade(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(@class, 'ng-binding')]")
    private WebElement welcome;
    @FindBy(xpath = "//span[@ng-show='noAccount']")
    private WebElement openAccountMessage;
    @FindBy(xpath = "//strong[@class='ng-binding']")
    private List<WebElement> accountInfoDisplayed;
    @FindBy(xpath = "//button[@ng-click='transactions()']")
    private WebElement transactionsButton;
    @FindBy(xpath = "//button[@ng-click='deposit()']")
    private WebElement depositButton;
    @FindBy(xpath = "//button[@ng-click='withdrawl()']")
    private WebElement withdrawlButton;
    @FindBy (xpath = "//strong[@class='ng-binding'][2]")
    private WebElement balanceInfo;
    @FindBy(xpath = "//button[@ng-click='byebye()']")
    private WebElement logoutButton;

    private final  String transactions = "Transactions";
    private final  String deposit = "Deposit";
    private final  String withdraw = "Withdraw";

    private TransactionsPage transactionsPage;
    private DepositPage depositPage;
    private WithdrawPage withdrawPage;

    public void validateAccountInfo(CustomerData customerData){
        AccountData accountData = customerData.getAccounts().get(0);
        Assert.assertTrue(assertionsMethods.validateText(welcome, customerData.getFullName()));
        List<String> customerAccountInfo = List.of(accountData.getAccountId(), accountData.getBalance(), accountData.getCurrency());
        Assert.assertTrue(assertionsMethods.validateText(accountInfoDisplayed, customerAccountInfo));
        LoggerUtility.info("Correct account info are displayed");
    }

    public void validateWelcomingNoAccount(CustomerData customerData) {
        Assert.assertTrue(assertionsMethods.validateText(welcome, customerData.getFullName()));
        Assert.assertTrue(assertionsMethods.validatePartialText(openAccountMessage, "open an account"));
        LoggerUtility.info("Validated successful message");

    }

    public void navigateToPage(String pageName) {

        switch (pageName) {
            case "Transactions" :
                transactionsPage = new TransactionsPage(driver);
                webElementsMethods.clickOn(transactionsButton);
                LoggerUtility.info("Clicked on Transactions button");
                break;
            case "Deposit" :
                depositPage = new DepositPage(driver);
                webElementsMethods.clickOn(depositButton);
                LoggerUtility.info("Clicked on Deposit button");
                break;
            case "Withdraw" :
                withdrawPage = new WithdrawPage(driver);
                webElementsMethods.clickOn(withdrawlButton);
                LoggerUtility.info("Clicked on Withdrawl button");
                break;
        }
    }

    public void depositMoney(CustomerData customerData) {
        navigateToPage(deposit);
        AccountData accountData = customerData.getAccounts().get(0);
        TransactionsData transactionsData = accountData.getTransactions().get(0);
        depositPage.deposit(transactionsData, customerData, accountData);
        List <String> info = List.of(getDateAndTIme(), transactionsData.getAmount(), "Credit");
        transactionsData.setDepositHistory(info);

        Assert.assertTrue(assertionsMethods.validateText(balanceInfo, accountData.getBalance()));
        LoggerUtility.info("Balance is correctly updated");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void withdrawMoney(CustomerData customerData) {
        navigateToPage(withdraw);
        AccountData accountData = customerData.getAccounts().get(0);
        TransactionsData transactionsData = accountData.getTransactions().get(0);
        withdrawPage.withdraw(transactionsData, customerData, accountData);
        List <String> info = List.of(getDateAndTIme(), transactionsData.getAmount(), "Debit");
        transactionsData.setWithdrawHistory(info);
        Assert.assertTrue(assertionsMethods.validateText(balanceInfo, accountData.getBalance()));
        LoggerUtility.info("Balance is correctly updated");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void validateTransactionHistory(CustomerData customerData) {
        navigateToPage(transactions);
        AccountData accountData = customerData.getAccounts().get(0);
        TransactionsData transactionsData = accountData.getTransactions().get(0);

        Assert.assertTrue(transactionsPage.validateDepositHistory(transactionsData));
        LoggerUtility.info("Validated deposit history");

        Assert.assertTrue(transactionsPage.validateWithdrawHistory(transactionsData));
        LoggerUtility.info("Validated withdraw history");
    }

    public void logout() {
        logoutButton.click();
        LoggerUtility.info("Clicked on logout button");
    }

    /////   helper methods ////

    private String getDateAndTIme() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(formatter);

    }


























}


