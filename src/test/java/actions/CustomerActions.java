package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.Transactions;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pageObjects.PageType;
import pageObjects.customer.CustomerAccountFacade;
import pageObjects.customer.TransactionsPage;
import java.util.List;


public class CustomerActions {

    private WebDriver driver;
    private CustomerAccountFacade customerAccountFacade;
    private TransactionsPage transactionsPage;


    public CustomerActions(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateToPage(String pageName) {
        customerAccountFacade = new CustomerAccountFacade(driver);

        customerAccountFacade.navigateToPage(pageName);

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Navigated to " + pageName);
    }

    public void selectAnAccount(Accounts account) {
        customerAccountFacade = new CustomerAccountFacade(driver);

        customerAccountFacade.selectAccountId(account.getAccountId());
    }

    public void depositMoney(Customers customer, Transactions transaction) {
        customerAccountFacade = new CustomerAccountFacade(driver);

        navigateToPage(PageType.DEPOSIT);
        customerAccountFacade.enterAmount(transaction.getAmount(), transaction.getType());
        customerAccountFacade.submitTransaction(transaction.getType(), transaction.getAmount());

        if (transaction.getAmount() != null) {
            customer.getAccounts().get(0).addToBalance(transaction.getAmount());
            customer.getAccounts().get(0).getTransactions().add(transaction);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void withdrawMoney(Customers customer, Transactions transaction) {
        customerAccountFacade = new CustomerAccountFacade(driver);

        navigateToPage(PageType.WITHDRAW);
        customerAccountFacade.enterAmount(transaction.getAmount(), transaction.getType());
        customerAccountFacade.submitTransaction(transaction.getType(), transaction.getAmount());

        boolean isInvalid = transaction.getAmount() == null || Integer.parseInt(transaction.getAmount()) > Integer.parseInt(customer.getAccounts().get(0).getBalance());

        if (! isInvalid) {
            customer.getAccounts().get(0).subtractFromBalance(transaction.getAmount());
            customer.getAccounts().get(0).getTransactions().add(transaction);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean validateAccountInfo(Accounts account) {

        List<String> expectedAccountInfo = List.of(account.getAccountId(), account.getBalance(), account.getCurrency());
        customerAccountFacade = new CustomerAccountFacade(driver);

        boolean isValid =  customerAccountFacade.getAccountInfo().equals(expectedAccountInfo);
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
