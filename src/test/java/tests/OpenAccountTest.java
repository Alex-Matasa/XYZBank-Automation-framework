package tests;

import dataObjects.CustomerData;
import dataObjects.DataModel;
import dataObjects.InputAccountData;
import dataObjects.InputCustomerData;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class OpenAccountTest extends Hooks {

    @Test
    public void openAccount() {

//        AddCustomerTest addCustomerTest = new AddCustomerTest();
//        addCustomerTest.addCustomer();

        DataModel dataModel = new DataModel("src/test/resources/testData/AddCustomer.json");
        DataModel dataModel1 = new DataModel("src/test/resources/testData/OpenAccount.json");
        InputCustomerData inputCustomerData = dataModel.inputCustomerData;
        InputAccountData inputAccountData = dataModel1.inputAccountData;
        CommonPage commonPage = new CommonPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        CustomerData customerData = new CustomerData();

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(inputCustomerData, customerData);
        bankManagerFacade.validateCustomer(customerData);
        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customerData);
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateWelcomingNoAccount(customerData);

        commonPage.clickOnHomeButton();
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.openAccount(inputAccountData,customerData);





    }




}
