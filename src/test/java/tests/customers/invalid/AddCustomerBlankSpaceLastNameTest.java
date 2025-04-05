package tests.customers.invalid;

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
import userFlows.TestPreconditions;
import validation.ExpectedMessages;
import validation.ValidationUtils;

public class AddCustomerBlankSpaceLastNameTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "invalidAddCustomer"})
    public void blankSpaceLastName() {
        DataModel dataModel = new DataModel(ResourcePath.BLANK_SPACE_LAST_NAME_DATA);
        Customers customer = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.navigateToAddCustomerPage(loginActions, bankManagerActions);
        bankManagerActions.addCustomer(customer);
        Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.WHITESPACE_AS_INPUT));
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Validated pop-up alert message");
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertFalse(bankManagerActions.isCustomerInTheTable(customer));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was not added to the list");
    }
}
