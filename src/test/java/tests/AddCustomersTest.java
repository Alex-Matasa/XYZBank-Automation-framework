package tests;

import actions.LoginActions;
import dataObjects.CustomerData;
import dataObjects.DataModel;
import org.testng.annotations.Test;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.CustomersPage;
import pageObjects.customer.CustomerAccountFacade;
import sharedData.Hooks;

public class AddCustomersTest extends Hooks {

    @Test
    public void addMultipleCustomers() {

        DataModel dataModel = new DataModel("src/test/resources/testData/AddMultipleCustomers.json");
        CustomersPage customersPage = new CustomersPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        CustomerAccountFacade customerAccountFacade = new CustomerAccountFacade(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        for (int i = 0; i < 5; i++) {
            CustomerData customerData = dataModel.customerData.get(i);
            bankManagerFacade.addCustomer(customerData);
            customersPage.validateLastEntry(customerData);
            loginActions.loginAsCustomer(customerData);
            customerAccountFacade.validateWelcomingNoAccount(customerData);
            loginActions.loginAsBankManager();
        }
    }


}
