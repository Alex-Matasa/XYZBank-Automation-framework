package tests.customers.valid;

import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.LoginActions;
import userFlows.TestPreconditions;

public class DeleteCustomerTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validDeleteCustomer"})
    public void searchByFirstNameSingleMatch() {
        DataModel dataModel = new DataModel(ResourcePath.DELETE_CUSTOMER_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.forSearchingCustomer(loginActions, bankManagerActions, customer, account);
        bankManagerActions.searchOrFilterCustomers(customer.getFirstName());
        bankManagerActions.deleteCustomer(customer);
        Assert.assertFalse(bankManagerActions.isCustomerInTheTable(customer));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Customer was deleted");
    }
}
