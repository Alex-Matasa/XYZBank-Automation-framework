package tests.customers.invalid;

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

public class AddCustomerLastNameEmptyTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "invalidAddCustomer"})
    public void lastNameEmpty() {
        DataModel dataModel = new DataModel(ResourcePath.LAST_NAME_EMPTY_DATA);
        Customers customer = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.navigateToAddCustomerPage(loginActions, bankManagerActions);
        bankManagerActions.addCustomer(customer);
        Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.FIELD_REQUIRED_MESSAGE));
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Validated pop-up alert message");
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertFalse(bankManagerActions.isCustomerInTheTable(customer));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was not added to the list");
    }
}
