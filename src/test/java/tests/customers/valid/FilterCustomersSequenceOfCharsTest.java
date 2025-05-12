package tests.customers.valid;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.LoginActions;

public class FilterCustomersSequenceOfCharsTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "customers", "validFilterCustomers"})
    public void filterCustomersSequenceOfChars() {
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        String charInput = "edo";

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        bankManagerActions.searchOrFilterCustomers(charInput);
        Assert.assertTrue(bankManagerActions.validateFilteredCustomers(charInput.toLowerCase()));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "Table is filtered properly");
    }
}
