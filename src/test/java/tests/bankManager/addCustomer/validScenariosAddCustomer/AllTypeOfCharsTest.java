package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class AllTypeOfCharsTest extends Hooks {

    @Test
    public void allTypeOfCharsTest() {
        DataModel dataModel = new DataModel(ResourcePath.ALL_TYPE_OF_CHARS_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToAddCustomer();
        bankManagerActions.addCustomer(customers);
        bankManagerActions.navigateToCustomersList();
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customers));
    }
}
