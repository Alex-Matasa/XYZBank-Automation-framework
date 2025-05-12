package tests.customers.valid;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import pageObjects.bankManager.CustomersPage;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.LoginActions;

public class FilterCustomersUnmatchingCharTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validFilterCustomers"})
    public void filterCustomersUnmatchedChar() {
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        CustomersPage customersPage = new CustomersPage(getDriver());
        String charInput = "-";

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        bankManagerActions.searchOrFilterCustomers(charInput);
        Assert.assertTrue(customersPage.getListOfCustomers().isEmpty());
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is empty ");
    }
}
