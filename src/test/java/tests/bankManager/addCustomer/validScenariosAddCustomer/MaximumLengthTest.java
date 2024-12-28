package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class MaximumLengthTest extends Hooks {

    @Test
    public void maximumLengthOfChars() {
        DataModel dataModel = new DataModel("src/test/resources/testData/addCustomer/validDataAddCustomer/maximumLength.json");
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.addCustomer(customers);
        Assert.assertTrue(bankManagerActions.validateLastCustomerAdded(customers));
    }


}