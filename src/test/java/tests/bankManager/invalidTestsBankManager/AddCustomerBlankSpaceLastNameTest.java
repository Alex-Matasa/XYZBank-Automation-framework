package tests.bankManager.invalidTestsBankManager;

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

public class AddCustomerBlankSpaceLastNameTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "addCustomer", "invalidAddCustomer"})
    public void blankSpaceLastName() {
        DataModel dataModel = new DataModel(ResourcePath.BLANK_SPACE_LAST_NAME_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customers);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);;
        Assert.assertFalse(bankManagerActions.isCustomerInTheList(customers));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was not added to the list");
    }
}
