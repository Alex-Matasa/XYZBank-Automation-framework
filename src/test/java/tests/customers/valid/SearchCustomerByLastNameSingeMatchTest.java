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

public class SearchCustomerByLastNameSingeMatchTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validSearchCustomer"})
    public void searchByLastNameSingleMatch() {
        DataModel dataModel = new DataModel(ResourcePath.SEARCH_FOR_A_CUSTOMER_SINGLE_MATCH_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.forSearchingCustomer(loginActions, bankManagerActions, customer, account);
        bankManagerActions.searchOrFilterCustomers(customer.getLastName());
        Assert.assertTrue(bankManagerActions.validateSearchCustomerSingleMatch(customer.getLastName(), 1));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is filtered properly");
    }
}
