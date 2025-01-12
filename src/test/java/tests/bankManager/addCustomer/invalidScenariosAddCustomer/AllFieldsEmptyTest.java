package tests.bankManager.addCustomer.invalidScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.bankManager.BankManagerFacade;
import sharedData.Hooks;

public class AllFieldsEmptyTest extends Hooks {

    @Test
    public void allFieldsEmpty() {
        DataModel dataModel = new DataModel(ResourcePath.ALL_FIELDS_EMPTY_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToAddCustomer();
        bankManagerActions.addCustomer(customers);
        bankManagerActions.navigateToCustomersList();
        Assert.assertFalse(bankManagerActions.isCustomerInTheList(customers));
    }

}
