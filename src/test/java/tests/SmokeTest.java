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

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(dataModel.customerData.get(0));
        bankManagerFacade.openAccount(dataModel.customerData.get(0));
        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(dataModel.customerData.get(0));
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateAccountInfo(dataModel.customerData.get(0));
        customerAccountFacade.depositMoney(dataModel.getCustomerData().get(0));
        customerAccountFacade.withdrawMoney(dataModel.getCustomerData().get(0));
        customerAccountFacade.validateTransactionHistory(dataModel.customerData.get(0));
        customerAccountFacade.logout();
        commonPage.clickOnHomeButton();
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.deleteCustomer(dataModel.getCustomerData().get(0));
    }




}
