package tests;

import dataObjects.CustomerData;
import dataObjects.DataModel;
import dataObjects.InputCustomerData;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

import java.util.ArrayList;
import java.util.List;

public class AddCustomerTest extends Hooks {

//    @Test
//    public void addCustomer() {
//        DataModel dataModel = new DataModel("src/test/resources/testData/AddCustomer.json");
//        InputCustomerData inputCustomerData = dataModel.inputCustomerData.get(0);
//        CommonPage commonPage = new CommonPage(getDriver());
//        LoginPage loginPage = new LoginPage(getDriver());
//        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
//        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
//        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
//        CustomerData customerData = new CustomerData();
//
//        loginPage.clickOnBankManagerLogin();
//        bankManagerFacade.addCustomer(inputCustomerData, customerData);
//        bankManagerFacade.validateCustomer(customerData);
//        commonPage.clickOnHomeButton();
//        loginPage.clickOnCustomerLogin();
//        customerLoginPage.selectName(customerData);
//        customerLoginPage.clickOnLoginButton();
//        customerAccountFacade.validateWelcomingNoAccount(customerData);
//    }

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
            InputCustomerData inputCustomerData = dataModel.getInputCustomerData().get(i);
            CustomerData customerData = new CustomerData();
            dataModel.getCustomerData().add(customerData);
            bankManagerFacade.addCustomer(inputCustomerData, dataModel.getCustomerData().get(i));
            bankManagerFacade.validateCustomer(customerData);
        }



//        commonPage.clickOnHomeButton();
//        loginPage.clickOnCustomerLogin();
//        customerLoginPage.selectName(customerData);
//        customerLoginPage.clickOnLoginButton();
//        customerAccountFacade.validateWelcomingNoAccount(customerData);
    }




}
