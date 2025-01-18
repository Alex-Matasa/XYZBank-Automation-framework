package tests.bankManager.addCustomer.validAddCustomer;

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

public class DuplicatedNameAndPostCode extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "addCustomer", "validAddCustomer"})
    public void duplicatedNameAndPostCode() {
        DataModel dataModel = new DataModel(ResourcePath.DUPLICATED_NAME_AND_POST_CODE_DATA);
        Customers customer1 = dataModel.customers.get(0);
        Customers customer2 = dataModel.customers.get(1);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer1);
        dataModel.standardizeInputData(customer1);
        bankManagerActions.addCustomer(customer2);
        dataModel.standardizeInputData(customer2);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer1));
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer2));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customers were added to the list");
    }
}
