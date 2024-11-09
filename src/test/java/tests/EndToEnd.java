package tests;

import dataObjects.*;
import org.testng.annotations.Test;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class EndToEnd extends Hooks {

    @Test
    public void entToEnd() {

        DataModelObject dataModelObject = new DataModelObject("src/test/resources/testData/E2E.json");
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(dataModelObject.customers.get(0));
        bankManagerFacade.openAccount(dataModelObject.customers.get(0));
        bankManagerFacade.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(dataModelObject.customers.get(0));
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateAccountInfo(dataModelObject.customers.get(0));
        customerAccountFacade.depositMoney(dataModelObject.getCustomers().get(0));
        customerAccountFacade.withdrawMoney(dataModelObject.getCustomers().get(0));
        customerAccountFacade.validateTransactionHistory(dataModelObject.customers.get(0));
        customerAccountFacade.logout();
        customerAccountFacade.clickOnHomeButton();
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.deleteCustomer(dataModelObject.getCustomers().get(0));

    }




}
