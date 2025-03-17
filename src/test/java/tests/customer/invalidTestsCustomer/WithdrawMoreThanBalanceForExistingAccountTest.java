package tests.customer.invalidTestsCustomer;

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

public class WithdrawMoreThanBalanceForExistingAccountTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "invalidMakeTransactions"})
    public void withdrawMoreThanBalanceForExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.WITHDRAW_MORE_THAN_BALANCE_FOR_AN_EXISTING_CUSTOMER);
        Customers customer = dataModel.customers.get(0);
        Accounts account = customer.getAccounts().get(0);
        Transactions transactionData = dataModel.transactions.get(0);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(account);
        customerActions.navigateToPage(PageType.WITHDRAW);
        customerActions.makeTransaction(account,transactionData);

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Transaction Failed");
    }
}
