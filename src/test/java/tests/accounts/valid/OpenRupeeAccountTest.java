package tests.accounts.valid;

import actions.BankManagerActions;
import actions.CustomerActions;
import actions.LoginActions;
import dataObjects.Accounts;
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

public class OpenRupeeAccountTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "accounts", "validOpenAccount"})
    public void openRupeeAccount() {
        DataModel dataModel = new DataModel(ResourcePath.OPEN_RUPEE_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        TestPreconditions.forOpeningAccount(loginActions, bankManagerActions, customer);
        bankManagerActions.openAccount(customer, account);
        Assert.assertTrue(ValidationUtils.alertMessageContainsText(ExpectedMessages.ACCOUNT_CREATED_ALERT_MESSAGE));
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isAccountAddedToTheList(customer));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "The account id is added to the list.");
        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(customer.getAccounts().get(0));
        Assert.assertTrue(customerActions.validateAccountInfo(customer.getAccounts().get(0)));
        ExtentUtility.addTestLog(StepType.PASS_STEP, "The account assigned to transactions contains correct data.");
    }
}
