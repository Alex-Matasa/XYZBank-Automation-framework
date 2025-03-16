package tests.bankManager.validTestsBankManager;

import actions.BankManagerActions;
import actions.LoginActions;
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

public class DeleteACustomerTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customersTable", "deleteCustomer"})
    public void deleteCustomer() {

        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        bankManagerActions.deleteFirstEntryCustomer();

        ExtentUtility.addTestLog(StepType.PASS_STEP, "First entry was deleted from the table");
    }
}
