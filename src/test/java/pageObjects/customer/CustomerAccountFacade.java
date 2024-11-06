package pageObjects.customer;


import dataObjects.CustomerAccountData;
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
    @FindBy(xpath = "//button[@class='btn home']")
    private WebElement homeButton;

    private final  String transactions = "Transactions";
    private final  String deposit = "Deposit";
    private final  String withdraw = "Withdraw";

    private TransactionsPage transactionsPage;
    private DepositPage depositPage;
    private WithdrawPage withdrawPage;

    public void validateAccountInfo(CustomerAccountData customerAccountData){
        Assert.assertTrue(assertionsMethods.validateText(welcome, customerAccountData.getFullName()));
        List<String> customerAccountInfo = List.of(customerAccountData.getAccountId(), customerAccountData.getBalance(), customerAccountData.getCurrency());
        Assert.assertTrue(assertionsMethods.validateText(accountInfoDisplayed, customerAccountInfo));
        LoggerUtility.info("Correct account info are displayed");
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

    public void depositMoney(CustomerAccountData customerAccountData, TransactionsData transactionsData) {
        navigateToPage(deposit);
        depositPage.deposit(transactionsData, customerAccountData);
        List <String> info = List.of(getDateAndTIme(), transactionsData.getDepositAmount(), "Credit");
        transactionsData.setDepositHistory(info);

        Assert.assertTrue(assertionsMethods.validateText(balanceInfo, customerAccountData.getBalance()));
        LoggerUtility.info("Balance is correctly updated");
    }

    public void withdrawMoney(CustomerAccountData customerAccountData, TransactionsData transactionsData) {
        navigateToPage(withdraw);
        withdrawPage.withdraw(transactionsData, customerAccountData);
        List <String> info = List.of(getDateAndTIme(), transactionsData.getWithdrawAmount(), "Debit");
        transactionsData.setWithdrawHistory(info);
        Assert.assertTrue(assertionsMethods.validateText(balanceInfo, customerAccountData.getBalance()));
        LoggerUtility.info("Balance is correctly updated");
    }

    public void validateTransactionHistory(TransactionsData transactionsData, CustomerAccountData customerAccountData) {
        navigateToPage(transactions);

        Assert.assertTrue(transactionsPage.validateDepositHistory(transactionsData));
        LoggerUtility.info("Validated deposit history");

        Assert.assertTrue(transactionsPage.validateWithdrawHistory(transactionsData));
        LoggerUtility.info("Validated withdraw history");
    }

    public void logout() {
        logoutButton.click();
        LoggerUtility.info("Clicked on logout button");
    }

    public void clickOnHomeButton() {
        webElementsMethods.clickOn(homeButton);
        LoggerUtility.info("Clicked on Home button");
    }


    /////   helper methods ////

    private String getDateAndTIme() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(formatter);

    }


























}


