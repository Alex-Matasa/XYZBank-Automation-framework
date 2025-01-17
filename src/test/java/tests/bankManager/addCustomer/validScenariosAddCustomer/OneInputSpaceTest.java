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

public class OneInputSpaceTest extends Hooks {

    @Test
    public void oneInputSpace() {
        DataModel dataModel = new DataModel(ResourcePath.ONE_INPUT_SPACE_DATA);
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customers);
        dataModel.standardizeInputData(customers);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customers));
    }
}
