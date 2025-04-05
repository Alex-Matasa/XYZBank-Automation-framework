package tests.transactions.valid;

import actions.BankManagerActions;
import actions.CustomerActions;
import actions.LoginActions;
import dataObjects.*;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;
import suites.TestSuite;
import userFlows.TestPreconditions;
import validation.ExpectedMessages;
import validation.ValidationUtils;

public class WithdrawMaximumAmountTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "validMakeTransactions"})
    public void withdrawMaximumAmount() {
        DataModel dataModel = new DataModel(ResourcePath.WITHDRAW_MAXIMUM_AMOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        Transactions transaction1 = dataModel.transactions.get(0);
        Transactions transaction2 = dataModel.transactions.get(1);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        TestPreconditions.forMakingATransaction(loginActions, bankManagerActions, customer, account);
        customerActions.depositMoney(customer, transaction1);
        customerActions.withdrawMoney(customer,transaction2);
        Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.WITHDRAW_SUCCESSFUL_MESSAGE));
        Assert.assertTrue(customerActions.validateAccountInfo(customer.getAccounts().get(0)));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Withdraw successful");
    }
}
