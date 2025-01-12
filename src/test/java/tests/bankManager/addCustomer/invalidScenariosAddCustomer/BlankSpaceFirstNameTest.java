package tests.bankManager.addCustomer.invalidScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class BlankSpaceFirstNameTest extends Hooks {

    @Test
    public void blankSpaceFirstName() {
        DataModel dataModel = new DataModel(ResourcePath.BLANK_SPACE_FIRST_NAME_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToAddCustomer();
        bankManagerActions.addCustomer(customers);
        bankManagerActions.navigateToCustomersList();
        Assert.assertFalse(bankManagerActions.isCustomerInTheList(customers));
    }
}
