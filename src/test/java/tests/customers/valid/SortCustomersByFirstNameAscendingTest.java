package tests.customers.valid;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import pageObjects.bankManager.CustomersPage;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.LoginActions;
import userFlows.TestPreconditions;

public class SortCustomersByFirstNameAscendingTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validSortCustomers"})
    public void sortByFirstNameAscending() {

        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        bankManagerActions.sortByFirstName(true);
        Assert.assertTrue(bankManagerActions.validateSortedByFirstName(true));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is sorted properly");
    }
}
