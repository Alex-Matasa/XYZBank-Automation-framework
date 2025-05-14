package tests.customers.valid;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.LoginActions;

public class SortCustomersByLastNameDescendingTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validSortCustomers"})
    public void sortByLastNameDescending() {

        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        bankManagerActions.sortByLastName(false);
        Assert.assertTrue(bankManagerActions.validateSortedByLastName(false));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is sorted properly");
    }
}
