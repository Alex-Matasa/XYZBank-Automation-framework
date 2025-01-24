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

public class AddCustomerDuplicatedDataTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "addCustomer", "invalidAddCustomer"})
    public void duplicatedData() {
        DataModel dataModel = new DataModel(ResourcePath.DUPLICATED_DATA_DATA);
        Customers customer1 = dataModel.customers.get(0);
        Customers customer2 = dataModel.customers.get(1);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer1);
        dataModel.standardizeInputData(customer1);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer1));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was added to the list");

        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer2);
        dataModel.standardizeInputData(customer2);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertFalse(bankManagerActions.isCustomerDuplicated(customer1));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was not duplicated");
    }
}
