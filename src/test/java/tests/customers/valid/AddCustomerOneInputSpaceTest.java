package tests.customers.valid;

import userActions.BankManagerActions;
import userActions.LoginActions;
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
import userFlows.TestPreconditions;
import validation.ExpectedMessages;
import validation.ValidationUtils;

public class AddCustomerOneInputSpaceTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validAddCustomer"})
    public void oneInputSpace() {
        DataModel dataModel = new DataModel(ResourcePath.ONE_INPUT_SPACE_DATA);
        Customers customer = dataModel.customers.get(0);
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        TestPreconditions.navigateToAddCustomerPage(loginActions, bankManagerActions);
        bankManagerActions.addCustomer(customer);
        Assert.assertTrue(ValidationUtils.alertMessageContainsText(ExpectedMessages.CUSTOMER_ADDED_ALERT_MESSAGE));
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Validated pop-up alert message");
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheTable(customer));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was added to the list");
    }
}
