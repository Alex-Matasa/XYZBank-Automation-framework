package tests.accounts.invalid;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;
import suites.TestSuite;
import userFlows.TestPreconditions;
import validation.ExpectedMessages;
import validation.ValidationUtils;

public class OpenAccountUnselectingCustomerNameTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "accounts", "invalidOpenAccount"})
    public void openAccountUnselectingCustomer() {
        LoginActions loginActions = new LoginActions(getDriver());
        DataModel dataModel = new DataModel(ResourcePath.LEAVE_CUSTOMER_NAME_UNSELECTED_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        TestPreconditions.navigateToOpenAccountPage(loginActions, bankManagerActions);
        bankManagerActions.openAccount(customer, account);
        Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.FIELD_REQUIRED_OPEN_ACCOUNT_MESSAGE));
        Assert.assertFalse(bankManagerActions.isAccountCreated(account));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "No account was created");
    }
}
