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

public class WithdrawNoAmountTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "transactions", "invalidTransactions"})
    public void withdrawNoAmount() {
        DataModel dataModel = new DataModel(ResourcePath.WITHDRAW_NO_AMOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        Transactions transaction = dataModel.transactions.get(0);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        TestPreconditions.forMakingATransaction(loginActions, bankManagerActions, customer, account);
        customerActions.withdrawMoney(customer.getAccounts().get(0), transaction);
        Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.FIELD_REQUIRED_MESSAGE));
        Assert.assertTrue(customerActions.validateAccountInfo(customer.getAccounts().get(0)));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "The account's balance is unchanged and a warning message pops-up");
    }
}
