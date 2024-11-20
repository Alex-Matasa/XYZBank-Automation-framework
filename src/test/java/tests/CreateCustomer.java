package tests;

import dataObjects.DataModel;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class CreateCustomer extends Hooks {

    @Test
    public void createCustomer() {
        DataModel dataModel = new DataModel("src/test/resources/testData/CreateCustomer.json");
        CommonPage commonPage = new CommonPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(dataModel.customerData.get(0));
        bankManagerFacade.validateCustomer(dataModel.customerData.get(0));
        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(dataModel.customerData.get(0));
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateWelcomingNoAccount(dataModel.customerData.get(0));
    }




}
