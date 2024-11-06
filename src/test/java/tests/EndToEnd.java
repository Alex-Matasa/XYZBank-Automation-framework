package tests;

import dataObjects.AddCustomerData;
import dataObjects.CustomerAccountData;
import dataObjects.OpenAccountData;
import dataObjects.TransactionsData;
import org.testng.annotations.Test;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class EndToEnd extends Hooks {

    @Test
    public void entToEnd() {

        AddCustomerData addCustomerData = new AddCustomerData("src/test/resources/testData/AddCustomerData.json");
        OpenAccountData openAccountData = new OpenAccountData("src/test/resources/testData/Currency.json");
        TransactionsData transactionsData = new TransactionsData("src/test/resources/testData/TransactionsAmounts.json");
        LoginPage loginPage = new LoginPage(getDriver());
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        CustomerAccountData customerAccountData = new CustomerAccountData();

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(addCustomerData, customerAccountData);
        bankManagerFacade.openAccount(addCustomerData, openAccountData, customerAccountData);
        bankManagerFacade.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customerAccountData);
        customerLoginPage.clickOnLoginButton();
        customerAccountFacade.validateAccountInfo(customerAccountData);
        customerAccountFacade.depositMoney(customerAccountData, transactionsData);
        customerAccountFacade.withdrawMoney(customerAccountData, transactionsData);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        customerAccountFacade.validateTransactionHistory(transactionsData, customerAccountData);
        customerAccountFacade.logout();
        customerAccountFacade.clickOnHomeButton();
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.deleteCustomer(addCustomerData);

    }




}
