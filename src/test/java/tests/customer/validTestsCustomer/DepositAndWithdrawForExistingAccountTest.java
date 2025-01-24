package tests.customer.validTestsCustomer;

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

public class DepositAndWithdrawForExistingAccountTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "validMakeTransactions"})
    public void depositAndWithdrawForExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.DEPOSIT_AND_WITHDRAW_FOR_EXISTING_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = customer.getAccounts().get(0);
        Transactions depositTransaction = dataModel.transactions.get(0);
        Transactions withdrawTransaction = dataModel.transactions.get(1);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(account);
        customerActions.navigateToPage(PageType.DEPOSIT);
        customerActions.makeTransaction(account,depositTransaction);
        customerActions.navigateToPage(PageType.WITHDRAW);
        customerActions.makeTransaction(account,withdrawTransaction);
        customerActions.navigateToPage(PageType.TRANSACTIONS);
        Assert.assertTrue(customerActions.validateTransactionsHistory(account));

        System.out.println(customer.getAccounts().get(0).getTransactions());

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Transaction was successfully added to the list");
    }
}
