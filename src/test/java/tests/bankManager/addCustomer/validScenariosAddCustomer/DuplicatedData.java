package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class DuplicatedData extends Hooks {

    @Test
    public void duplicatedData() {
        DataModel dataModel = new DataModel("src/test/resources/testData/addCustomer/validDataAddCustomer/duplicatedData.json");
        Customers customer1 = dataModel.customers.get(0);
        Customers customer2 = dataModel.customers.get(1);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.addCustomer(customer1);
        dataModel.normalizeInputData(customer1);
        bankManagerActions.addCustomer(customer2);
        dataModel.normalizeInputData(customer2);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer1));
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customer2));
    }


}
