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

public class SortCustomersByPostCodeDescendingTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validSortCustomers"})
    public void sortByPostCodeDescending() {

        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        bankManagerActions.sortByPostCode(false);
        Assert.assertTrue(bankManagerActions.validateSortedByPostCode(false));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is sorted properly");
    }
}
