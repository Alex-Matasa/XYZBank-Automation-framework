package tests.bankManager.invalidTestsBankManager;

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
import pageObjects.PageType;
import sharedData.Hooks;
import suites.TestSuite;

public class OpenAccountUnselectingCustomerNameTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "openAccount", "invalidOpenAccount"})
    public void openNewAccountForNewCustomer() {
        LoginActions loginActions = new LoginActions(getDriver());
        DataModel dataModel = new DataModel(ResourcePath.LEAVE_CUSTOMER_NAME_UNSELECTED_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
        bankManagerActions.openAccount(customer, account);
        Assert.assertTrue(bankManagerActions.isAccountCreated(account));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "No account was created");
    }
}
