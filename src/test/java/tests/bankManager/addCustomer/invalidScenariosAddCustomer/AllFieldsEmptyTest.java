package tests.bankManager.addCustomer.invalidScenariosAddCustomer;

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
import pageObjects.bankManager.BankManagerFacade;
import sharedData.Hooks;
import suites.TestSuite;

public class AllFieldsEmptyTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "addCustomer", "invalidAddCustomer"})
    public void allFieldsEmpty() {
        DataModel dataModel = new DataModel(ResourcePath.ALL_FIELDS_EMPTY_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        ExtentUtility.addTestLog(StepType.PASS_STEP, "I performed login process as Bank Manager");

        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        ExtentUtility.addTestLog(StepType.PASS_STEP, "I navigated to Add Customer page");

        bankManagerActions.addCustomer(customers);
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was added");

        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertFalse(bankManagerActions.isCustomerInTheList(customers));
    }
}
