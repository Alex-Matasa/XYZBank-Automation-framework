package tests.transactions.invalid;

import dataObjects.*;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.CustomerActions;
import userActions.LoginActions;
import userFlows.TestPreconditions;
import validation.ExpectedMessages;
import validation.ValidationUtils;

public class WithdrawMoreThanBalanceTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "invalidMakeTransactions"})
    public void withdrawMoreThanBalance() {
        DataModel dataModel = new DataModel(ResourcePath.WITHDRAW_MORE_THAN_BALANCE_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        Transactions transaction1 = dataModel.transactions.get(0);
        Transactions transaction2 = dataModel.transactions.get(1);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        TestPreconditions.forMakingATransaction(loginActions, bankManagerActions, customer, account);
        customerActions.depositMoney(customer.getAccounts().get(0), transaction1);
        customerActions.withdrawMoney(customer.getAccounts().get(0), transaction2);
        Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.INSUFFICIENT_FUNDS_MESSAGE));
        Assert.assertTrue(customerActions.validateAccountInfo(customer.getAccounts().get(0)));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Transaction failed.");
    }
}
