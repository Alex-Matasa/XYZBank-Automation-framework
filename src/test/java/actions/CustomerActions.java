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


public class CustomerActions {

    private WebDriver driver;
    private CustomerAccountFacade customerAccountFacade;
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
        account.setBalance(customerAccountFacade.getActualAccountInfo().get(1));

        customerAccountFacade.enterAmount(transaction.getAmount(), transaction.getType());
        customerAccountFacade.submitTransaction(transaction.getAmount(),transaction.getType());

        transaction.setDateAndTime();

        if(transaction.getType().equals("Credit")) account.addToBalance(transaction.getAmount());
        else account.subtractFromBalance(transaction.getAmount());

        if (account.getTransactions() == null) account.setTransactions(new ArrayList<>());

        account.getTransactions().add(transaction);

        Assert.assertEquals(customerAccountFacade.getActualAccountInfo().get(1), account.getBalance());

        LoggerUtility.info("The balance was properly updated.");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean validateAccountInfo(Accounts account) {
        List<String> expectedAccountInfo = List.of(account.getAccountId(), account.getBalance(), account.getCurrency());

        customerAccountFacade = new CustomerAccountFacade(driver);

        boolean isValid =  customerAccountFacade.getActualAccountInfo().equals(expectedAccountInfo);

        if(isValid) LoggerUtility.info("Account data info displayed are valid");

        return isValid;
    }

    public boolean validateTransactionsHistory(Accounts account) {
        transactionsPage = new TransactionsPage(driver);

        List<String> tableTransactions = transactionsPage.getTransactionsHistory();
        List<String> accountTransactions = account.getTransactions().stream()
                .map(transaction -> transaction.getTime() + " " + transaction.getAmount() + " " + transaction.getType())
                .toList();

        boolean isValid = true;

        for (String accountTransaction : accountTransactions) {
            if (!tableTransactions.contains(accountTransaction)) {
                isValid = false;
                break;
            }
        }

        if(isValid) LoggerUtility.info("All transaction are displayed in the table");

        return isValid;
    }
}
