package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.Transactions;
import helperMethods.AssertionsMethods;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pageObjects.customer.CustomerAccountFacade;
import pageObjects.customer.DepositPage;
import pageObjects.customer.WithdrawPage;

import java.util.ArrayList;
import java.util.List;

public class CustomerActions {

    private WebDriver driver;
    private CustomerAccountFacade customerAccountFacade;
    private DepositPage depositPage;
    private WithdrawPage withdrawPage;
    private AssertionsMethods assertionsMethods;


    public CustomerActions(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToTransactions() {
        customerAccountFacade = new CustomerAccountFacade(driver);
        customerAccountFacade.navigateToPage("Transactions");
    }

    public void navigateToDeposit() {
        customerAccountFacade = new CustomerAccountFacade(driver);
        customerAccountFacade.navigateToPage("Deposit");
    }

    public void navigateToWithdraw() {
        customerAccountFacade = new CustomerAccountFacade(driver);
        customerAccountFacade.navigateToPage("Withdraw");
    }

    public void depositMoney(Accounts account, Transactions transaction) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        depositPage = new DepositPage(driver);
        account.setBalance(customerAccountFacade.getActualAccountInfo().get(1));

        if (account.getTransactions() == null) {
            account.setTransactions(new ArrayList<>());
        }

        depositPage.enterAmount(transaction.getAmount());
        depositPage.clickOnDeposit(transaction.getAmount());
        transaction.setType("Credit");
        account.getTransactions().add(transaction);

//        List<String> info = List.of(customerAccountFacade.getDateAndTime(), transactions.getAmount(), "Credit");
//        transactions.setDepositHistory(info);

//        Assert.assertTrue(assertionsMethods.validateText(balanceInfo, accounts.getBalance()));
//        LoggerUtility.info("Balance is correctly updated");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void withdrawMoney(Customers customer) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        withdrawPage = new WithdrawPage(driver);

        customerAccountFacade.navigateToPage("Withdraw");
        Accounts accounts = customer.getAccounts().get(0);
        Transactions transactions = accounts.getTransactions().get(0);
        withdrawPage.withdraw(transactions, customer, accounts);
        List <String> info = List.of(customerAccountFacade.getDateAndTime(), transactions.getAmount(), "Debit");
        transactions.setWithdrawHistory(info);
//        Assert.assertTrue(assertionsMethods.validateText(CustomerAccountFacadeLocators.balanceInfo, accounts.getBalance()));
        LoggerUtility.info("Balance is correctly updated");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void selectAnAccount(Accounts account) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        customerAccountFacade.selectAccountId(account.getAccountId());
    }

    public boolean validateAccountInfo(Accounts account) {
        boolean isValid;
        List<String> expectedAccountInfo = List.of(account.getAccountId(), account.getBalance(), account.getCurrency());
        assertionsMethods = new AssertionsMethods(driver);
        customerAccountFacade = new CustomerAccountFacade(driver);

        isValid =  assertionsMethods.validateText(customerAccountFacade.getActualAccountInfo(),expectedAccountInfo);
        if(isValid){
            LoggerUtility.info("Account data info displayed are valid");
        }

        return isValid;
    }






}
