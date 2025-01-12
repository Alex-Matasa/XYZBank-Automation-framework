package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class DuplicatedNames extends Hooks {

    @Test
    public void duplicatedNames() {
        DataModel dataModel = new DataModel(ResourcePath.DUPLICATED_NAMES_DATA);
        Customers customer1 = dataModel.customers.get(0);
        Customers customer2 = dataModel.customers.get(1);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToAddCustomer();
        bankManagerActions.addCustomer(customer1);
        dataModel.normalizeInputData(customer1);
        bankManagerActions.addCustomer(customer2);
        dataModel.normalizeInputData(customer2);
        bankManagerActions.navigateToCustomersList();
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer1));
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer2));
    }
}
