package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import pageObjects.bankManager.CustomersPage;
import sharedData.Hooks;

public class Test extends Hooks {

    @org.testng.annotations.Test
    public void allTypeOfCharsTest() {
        DataModel dataModel = new DataModel("src/test/resources/testData/addCustomer/validDataAddCustomer/allTypeOfChars.json");
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        CustomersPage customersPage = new CustomersPage(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToCustomersList();
        System.out.println(customersPage.getListOfCustomers());

    }
}
