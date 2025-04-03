package tests.loginLogout;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CustomerLoginPage;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;
import suites.TestSuite;
import userFlows.TestPreconditions;

public class LogoutAsCustomerTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "loginLogout"})
    public void loginAsCustomerWithAccountTest() {
        DataModel dataModel = new DataModel(ResourcePath.LOGOUT_AS_CUSTOMER_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());

        TestPreconditions.forLogOutAsCustomer(loginActions, bankManagerActions, customer, account);
        customerAccountFacade.clickOnLogoutButton();
        Assert.assertTrue(customerLoginPage.validateCustomerLoginDashboard());
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Customer is logged out");
    }
}
