package userActions;

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
import validation.ValidationUtils;

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

    public void depositMoney(Accounts account, Transactions transaction) {
        customerAccountFacade = new CustomerAccountFacade(driver);

        navigateToPage(PageType.DEPOSIT);
        customerAccountFacade.enterAmount(transaction.getAmount(), transaction.getType());
        customerAccountFacade.submitTransaction(transaction.getType(), transaction.getAmount());

        if (transaction.getAmount() != null) {
            account.addToBalance(transaction.getAmount());
            transaction.setDateAndTime();
            account.getTransactions().add(transaction);
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void withdrawMoney(Accounts account, Transactions transaction) {
        customerAccountFacade = new CustomerAccountFacade(driver);

        navigateToPage(PageType.WITHDRAW);
        customerAccountFacade.enterAmount(transaction.getAmount(), transaction.getType());
        customerAccountFacade.submitTransaction(transaction.getType(), transaction.getAmount());

        boolean isInvalid = transaction.getAmount() == null || Integer.parseInt(transaction.getAmount()) > Integer.parseInt(account.getBalance());

        if (! isInvalid) {
            account.subtractFromBalance(transaction.getAmount());
            transaction.setDateAndTime();
            account.getTransactions().add(transaction);
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
        customerAccountFacade = new CustomerAccountFacade(driver);

        customerAccountFacade.navigateToPage(PageType.TRANSACTIONS);
        List<String> actualTransactions = transactionsPage.getTransactionsHistory();
        List<String> expectedTransactions = account.getTransactions().stream()
                .map(transaction -> transaction.getTime() + " " + transaction.getAmount() + " " + transaction.getType())
                .toList();

        boolean isValid = true;

        for (String transaction : expectedTransactions) {
            if (!actualTransactions.equals(expectedTransactions)) {
                isValid = false;
                break;
            }
        }

        if(isValid) LoggerUtility.info("All transaction are displayed in the table");

        transactionsPage.clickOnBackButton();

        return isValid;
    }

}
