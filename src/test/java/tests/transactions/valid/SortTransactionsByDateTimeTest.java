package tests.transactions.valid;

import dataObjects.*;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.CustomerActions;
import userActions.LoginActions;
import userFlows.Flows;

public class SortTransactionsByDateTimeTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "validMakeTransactions"})
    public void validateTransactionHistory() {
        DataModel dataModel = new DataModel(ResourcePath.SORT_TRANSACTION_BY_DATE_TIME_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        Transactions transaction1 = dataModel.transactions.get(0);
        Transactions transaction2 = dataModel.transactions.get(1);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        Flows.addCustomer(loginActions, bankManagerActions, customer);
        Flows.openAccount(loginActions, bankManagerActions, customer, account);
        loginActions.loginAsCustomer(customer);
        customerActions.depositMoney(customer.getAccounts().get(0), transaction1);
        customerActions.withdrawMoney(customer.getAccounts().get(0), transaction2);
        customerActions.navigateToPage(PageType.TRANSACTIONS);
        Assert.assertTrue(customerActions.validateOldestToNewest(customer.getAccounts().get(0)));
        Assert.assertTrue(customerActions.validateNewestToOldest(customer.getAccounts().get(0)));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Transactions are sorted properly");
    }
}
