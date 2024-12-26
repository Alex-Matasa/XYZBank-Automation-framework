package tests;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.annotations.Test;
import pageObjects.bankManager.CustomersPage;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class AddCustomerTest extends Hooks {

    @Test
    public void addCustomer() {
        DataModel dataModel = new DataModel("src/test/resources/testData/AddCustomer.json");
        Customers customers = dataModel.customers.get(0);
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        CustomersPage customersPage = new CustomersPage(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.addCustomer(customers);
        bankManagerActions.validateCustomer(customers);

//        customersPage.validateLastEntry(customerData);
//        loginActions.loginAsCustomer(customerData);
//        customerAccountFacade.validateWelcomingNoAccount(customerData);
    }




}
