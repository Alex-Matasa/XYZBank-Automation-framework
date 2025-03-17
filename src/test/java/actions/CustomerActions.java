package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.Transactions;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

        ExtentUtility.addTestLog(StepType.INFO_STEP, "The account was selected");
    }

    public void makeTransaction(Accounts account, Transactions transactionTestData) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        account.setBalance(customerAccountFacade.getActualAccountInfo().get(1));

        customerAccountFacade.enterAmount(transactionTestData.getAmount(), transactionTestData.getType());
        customerAccountFacade.submitTransaction(transactionTestData.getAmount(),transactionTestData.getType(), account.getBalance());

        if((!transactionTestData.getType().equals("Debit")) && (Integer.parseInt(transactionTestData.getAmount()) > Integer.parseInt(account.getBalance()))) {
            Transactions newTransaction = new Transactions();
            newTransaction.setDateAndTime();
            newTransaction.setType(transactionTestData.getType());
            newTransaction.setTime(transactionTestData.getTime());
            newTransaction.setAmount(transactionTestData.getAmount());

            account.getTransactions().add(newTransaction);

            if(newTransaction.getType().equals("Credit")) account.addToBalance(newTransaction.getAmount());
            else account.subtractFromBalance(newTransaction.getAmount());

            Assert.assertEquals(customerAccountFacade.getActualAccountInfo().get(1), account.getBalance());

            if(newTransaction.getType().equals("Credit")) ExtentUtility.addTestLog(StepType.INFO_STEP, "Deposit transaction was made");
            else ExtentUtility.addTestLog(StepType.INFO_STEP, "Withdraw transaction was made");

            LoggerUtility.info("The balance was properly updated.");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
