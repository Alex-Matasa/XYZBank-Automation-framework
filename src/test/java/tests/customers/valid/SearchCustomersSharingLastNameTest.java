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

public class SearchCustomersSharingLastNameTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validSearchCustomer"})
    public void searchByLastNameMultipleMatches() {
        DataModel dataModel = new DataModel(ResourcePath.SEARCH_CUSTOMERS_SHARING_LAST_NAME_DATA);
        Customers customer1 = dataModel.customers.get(0);
        Customers customer2 = dataModel.customers.get(1);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.forSearchingCustomer(loginActions, bankManagerActions, customer1, account);
        TestPreconditions.forSearchingCustomer(loginActions, bankManagerActions, customer2, account);
        bankManagerActions.searchForACustomer(customer1.getLastName());
        Assert.assertTrue(bankManagerActions.validateFilteredTableMultipleMatches(customer1.getLastName(), 2));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is filtered properly");
    }
}
