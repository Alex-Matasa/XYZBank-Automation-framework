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

public class AddCustomerMinimumLengthTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "addCustomer", "validAddCustomer"})
    public void minimumLengthOfChars() {
        DataModel dataModel = new DataModel(ResourcePath.MINIMUM_LENGTH_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customers);
        dataModel.standardizeInputData(customers);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customers));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was added to the list");
    }
}
