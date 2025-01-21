package tests.bankManager.openAccount.validScenariosOpenAccount;

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
import pageObjects.CommonPage;
import pageObjects.PageType;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;
import suites.TestSuite;

public class OpenNewAccountForNewCustomer extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "openAccount", "validOpenAccount"})
    public void openNewAccountForNewCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.NEW_ACCOUNT_FOR_NEW_CUSTOMER_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.accounts.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        CommonPage commonPage = new CommonPage(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer);
        commonPage.navigateToHomePage();
        loginActions.loginAsCustomer(customer);
        Assert.assertTrue(customerAccountFacade.validateCustomerHasNoAccount(customer));
        customerAccountFacade.logout();
        commonPage.navigateToHomePage();
        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
        bankManagerActions.openAccount(customer, account);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isAccountAddedToTheList(customer));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "The account id is added to the list.");

        commonPage.navigateToHomePage();
        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(customer.getAccounts().get(0));
        Assert.assertTrue(customerActions.validateAccountInfo(customer.getAccounts().get(0)));

        ExtentUtility.addTestLog(StepType.PASS_STEP, "The account assigned to customer contains correct data.");
    }
}
