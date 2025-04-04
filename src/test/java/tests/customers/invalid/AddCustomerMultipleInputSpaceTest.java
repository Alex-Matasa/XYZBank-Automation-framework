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

public class AddCustomerMultipleInputSpaceTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "invalidAddCustomer"})
    public void multipleInputSpace() {
        DataModel dataModel = new DataModel(ResourcePath.MULTIPLE_INPUT_SPACES_DATA);
        Customers customer = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.navigateToAddCustomerPage(loginActions, bankManagerActions);
        bankManagerActions.addCustomer(customer);
        customer.sanitizeData();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheTable(customer));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Whitespaces were trimmed and the transactions was added to the list");
    }
}
