package tests.bankManager.addCustomer.invalidScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class FirstNameEmptyTest extends Hooks {

    @Test
    public void firstNameEmpty() {
        DataModel dataModel = new DataModel("src/test/resources/testData/addCustomer/invalidDataAddCustomer/firstNameEmpty.json");
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.fillHalfForm(customers);
        Assert.assertFalse(bankManagerActions.isCustomerInTheList(customers));
    }

}
