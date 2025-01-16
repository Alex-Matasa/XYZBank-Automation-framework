package actions;

import dataObjects.Accounts;
import dataObjects.Transactions;
import helperMethods.AssertionsMethods;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.customer.CustomerAccountFacade;
import pageObjects.customer.TransactionsPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerActions {

    private WebDriver driver;
    private CustomerAccountFacade customerAccountFacade;
    private AssertionsMethods assertionsMethods;
    private TransactionsPage transactionsPage;


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

    public void selectAnAccount(Accounts account) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        customerAccountFacade.selectAccountId(account.getAccountId());
    }

    public void makeTransaction(Accounts account, Transactions transaction) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        assertionsMethods = new AssertionsMethods(driver);
        account.setBalance(customerAccountFacade.getActualAccountInfo().get(1));

        customerAccountFacade.enterAmount(transaction.getAmount(), transaction.getType());
        customerAccountFacade.submitTransaction(transaction.getAmount(),transaction.getType());
        transaction.setDateAndTime();

        if(transaction.getType().equals("Credit")) account.addToBalance(transaction.getAmount());
        else account.subtractFromBalance(transaction.getAmount());

        if (account.getTransactions() == null) {
            account.setTransactions(new ArrayList<>());
        }
        account.getTransactions().add(transaction);

        Assert.assertTrue(assertionsMethods.actualEqualExpected(account.getBalance(), customerAccountFacade.getActualAccountInfo().get(1)));
        LoggerUtility.info("The balance was properly updated.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean validateAccountInfo(Accounts account) {
        boolean isValid;
        List<String> expectedAccountInfo = List.of(account.getAccountId(), account.getBalance(), account.getCurrency());
        assertionsMethods = new AssertionsMethods(driver);
        customerAccountFacade = new CustomerAccountFacade(driver);

        isValid =  assertionsMethods.actualEqualExpected(customerAccountFacade.getActualAccountInfo(),expectedAccountInfo);
        if(isValid){
            LoggerUtility.info("Account data info displayed are valid");
        }

        return isValid;
    }

    public boolean validateTransactionsHistory(Accounts account) {
        transactionsPage = new TransactionsPage(driver);
        assertionsMethods = new AssertionsMethods(driver);

        List<String> tableTransactions = transactionsPage.getTransactionsHistory();
        List<String> accountTransactions = account.getTransactions().stream()
                .map(Transactions::toString)
                .toList();

        boolean isValid = account.getTransactions().stream()
                .map(Transactions::toString)
                .allMatch(tableTransactions::contains);

        if(isValid) LoggerUtility.info("All transaction are displayed in the table");

        return isValid;
    }









}
