package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.Transactions;
import org.openqa.selenium.WebDriver;
import pageObjects.customer.CustomerAccountFacade;
import pageObjects.customer.DepositPage;

import java.util.List;

public class CustomerActions {

    private WebDriver driver;

    private CustomerAccountFacade customerAccountFacade;
    private DepositPage depositPage;



    public CustomerActions(WebDriver driver) {
        this.driver = driver;
    }

    public void depositMoney(Customers customers) {
        customerAccountFacade = new CustomerAccountFacade(driver);
        depositPage = new DepositPage(driver);

        customerAccountFacade.navigateToPage("Deposit");
        Accounts accounts = customers.getAccounts().get(0);
        Transactions transactions = accounts.getTransactions().get(0);
        depositPage.deposit(transactions, customers, accounts);
        List<String> info = List.of(customerAccountFacade.getDateAndTime(), transactions.getAmount(), "Credit");
        transactions.setDepositHistory(info);

//        Assert.assertTrue(assertionsMethods.validateText(balanceInfo, accounts.getBalance()));
//        LoggerUtility.info("Balance is correctly updated");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
