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

public class AddCustomerDuplicatedDataTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "invalidAddCustomer"})
    public void duplicatedData() {
        DataModel dataModel = new DataModel(ResourcePath.DUPLICATED_DATA_DATA);
        Customers customer1 = dataModel.customers.get(0);
        Customers customer2 = dataModel.customers.get(1);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.navigateToAddCustomerPage(loginActions, bankManagerActions);
        bankManagerActions.addCustomer(customer1);
        Assert.assertTrue(ValidationUtils.alertMessageContainsText(ExpectedMessages.CUSTOMER_ADDED_ALERT_MESSAGE));
        bankManagerActions.addCustomer(customer2);
        Assert.assertTrue(ValidationUtils.alertMessageContainsText(ExpectedMessages.DUPLICATE_CUSTOMER));
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertFalse(bankManagerActions.isCustomerDuplicated(customer1));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "The transactions was not  duplicated");
    }
}
