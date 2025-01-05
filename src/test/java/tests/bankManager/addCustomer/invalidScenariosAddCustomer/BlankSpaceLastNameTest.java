package tests.bankManager.addCustomer.invalidScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class BlankSpaceLastNameTest extends Hooks {

    @Test
    public void blankSpaceLastName() {
        DataModel dataModel = new DataModel("src/test/resources/testData/addCustomer/invalidDataAddCustomer/blankSpaceLastName.json");
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToAddCustomer();
        bankManagerActions.addCustomer(customers);
        dataModel.normalizeInputData(customers);
        bankManagerActions.navigateToCustomersList();
        Assert.assertFalse(bankManagerActions.isCustomerInTheList(customers));
    }
}
