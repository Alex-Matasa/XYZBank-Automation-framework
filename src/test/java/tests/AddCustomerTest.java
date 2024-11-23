package tests;

import actions.BankManagerActions;
import dataObjects.CustomerData;
import dataObjects.DataModel;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class AddCustomerTest extends Hooks {

    @Test
    public void addCustomer() {
        DataModel dataModel = new DataModel("src/test/resources/testData/AddCustomer.json");
        CustomerData customerData = dataModel.customerData.get(0);
        CommonPage commonPage = new CommonPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        bankManagerActions.addNewCustomer(customerData);
        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customerData);
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateWelcomingNoAccount(customerData);
    }




}
