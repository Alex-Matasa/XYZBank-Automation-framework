package tests.customer.makeTransaction.validScenariosMakeTransaction;

import actions.CustomerActions;
import actions.LoginActions;
import dataObjects.*;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import sharedData.Hooks;
import suites.TestSuite;

public class DepositForExistingAccount extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "validMakeTransactions"})
    public void depositForExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.DEPOSIT_FOR_EXISTING_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = customer.getAccounts().get(0);
        Transactions transaction = dataModel.transactions.get(0);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(account);
        customerActions.navigateToPage(PageType.DEPOSIT);
        customerActions.makeTransaction(account,transaction);
        customerActions.navigateToPage(PageType.TRANSACTIONS);
        Assert.assertTrue(customerActions.validateTransactionsHistory(account));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Transaction was successfully added to the list");
    }
}
