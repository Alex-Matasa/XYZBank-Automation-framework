package tests.bankManager.openAccount.validScenariosOpenAccount;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class ForExistingCustomer extends Hooks {

    @Test
    public void openAccountForAnnExistingCustomer() {
        DataModel dataModel = new DataModel("src/test/resources/testData/openAccount/validDataOpenAccount/forAnExistingCustomer.json");
        Customers customer = dataModel.customers.get(0);
        Accounts accounts = dataModel.getAccounts().get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToOpenAccount();
        bankManagerActions.openAccount(customer, accounts);
        bankManagerActions.navigateToCustomersList();
//        Assert.assertTrue(bankManagerActions.isAccountAddedToTable(customer));
    }
}
