package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
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
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer1);
        dataModel.standardizeInputData(customer1);
        bankManagerActions.addCustomer(customer2);
        dataModel.standardizeInputData(customer2);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer1));
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer2));
    }
}
