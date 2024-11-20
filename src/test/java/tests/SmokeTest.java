package tests;

import dataObjects.*;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class SmokeTest extends Hooks {

    @Test
    public void smoke() {
        DataModel dataModel = new DataModel("src/test/resources/testData/Smoke.json");
        CommonPage commonPage = new CommonPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        CustomerData customerData = dataModel.customerData.get(0);

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(customerData);
        bankManagerFacade.openAccount(customerData);
        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customerData);
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateAccountInfo(customerData);
        customerAccountFacade.depositMoney(customerData);
        customerAccountFacade.withdrawMoney(customerData);
        customerAccountFacade.validateTransactionHistory(customerData);
        customerAccountFacade.logout();
        commonPage.clickOnHomeButton();
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.deleteCustomer(customerData);
    }




}
