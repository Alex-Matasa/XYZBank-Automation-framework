package tests.bankManager.addCustomer.validScenariosAddCustomer;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class MinimumLengthTest extends Hooks {

    @Test
    public void minimumLengthOfChars() {
        DataModel dataModel = new DataModel("src/test/resources/testData/addCustomer/validDataAddCustomer/minimumLength.json");
        Customers customers = dataModel.customers.get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.addCustomer(customers);
        dataModel.normalizeInputData(customers);
        Assert.assertTrue(bankManagerActions.isCustomerInTheList(customers));
    }


}
