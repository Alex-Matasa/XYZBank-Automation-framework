package tests;

import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.annotations.Test;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.CustomersPage;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class AddMultipleCustomersTest extends Hooks {

    @Test
    public void addMultipleCustomers() {

        DataModel dataModel = new DataModel("src/test/resources/testData/AddMultipleCustomers.json");
        CustomersPage customersPage = new CustomersPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        for (int i = 0; i < 5; i++) {
            Customers customers = dataModel.customers.get(i);
//            bankManagerFacade.addCustomer(customerData);
//            customersPage.validateLastEntry(customers);
            loginActions.loginAsCustomer(customers);
            customerAccountFacade.validateWelcomingNoAccount(customers);
            loginActions.loginAsBankManager();
        }
    }


}
