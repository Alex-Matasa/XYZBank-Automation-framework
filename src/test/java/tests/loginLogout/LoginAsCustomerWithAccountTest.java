package tests.loginLogout;

import userActions.BankManagerActions;
import userActions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;
import suites.TestSuite;
import userFlows.TestPreconditions;

public class LoginAsCustomerWithAccountTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "loginLogout"})
    public void loginAsCustomerWithAccountTest() {
        DataModel dataModel = new DataModel(ResourcePath.LOGIN_AS_CUSTOMER_WITH_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());

        TestPreconditions.forLogInAsCustomer(loginActions, bankManagerActions, customer, account);
        loginActions.loginAsCustomer(customer);
        Assert.assertTrue(customerAccountFacade.validateWelcomingMessage(customer));
        Assert.assertTrue(customerAccountFacade.validateCustomerAccountDashboard());
        ExtentUtility.addTestLog(StepType.PASS_STEP, "The user is logged in");
    }
}
