package tests;

import dataObjects.CustomerData;
import dataObjects.DataModel;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class CreateCustomerTest extends Hooks {

    @Test
    public void createCustomer() {
        DataModel dataModel = new DataModel("src/test/resources/testData/CreateCustomer.json");
        CommonPage commonPage = new CommonPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        CustomerData customerData = dataModel.customerData.get(0);

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(customerData);
        bankManagerFacade.validateCustomer(customerData);
        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customerData);
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateWelcomingNoAccount(customerData);
    }




}
