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

public class AddCustomersTest extends Hooks {

    @Test
    public void addMultipleCustomers() {
        DataModel dataModel = new DataModel("src/test/resources/testData/AddMultipleCustomers.json");



        CommonPage commonPage = new CommonPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());


        loginPage.clickOnBankManagerLogin();

        for (int i = 0; i < 5; i++) {
            CustomerData customerData = dataModel.customerData.get(i);
            bankManagerFacade.addCustomer(customerData);
            bankManagerFacade.validateCustomer(customerData);
            commonPage.clickOnHomeButton();
            loginPage.clickOnCustomerLogin();
            customerLoginPage.selectName(customerData);
            customerLoginPage.clickOnLoginButton();
            customerAccountFacade.validateWelcomingNoAccount(customerData);
            commonPage.clickOnHomeButton();
            loginPage.clickOnBankManagerLogin();
        }


    }




}
