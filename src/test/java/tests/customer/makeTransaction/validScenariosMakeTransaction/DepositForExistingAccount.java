package tests.customer.makeTransaction.validScenariosMakeTransaction;

import actions.BankManagerActions;
import actions.CustomerActions;
import actions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class DepositForExistingAccount extends Hooks {

    @Test
    public void depositForExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.DEPOSIT_FOR_EXISTING_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts accounts = dataModel.getAccounts().get(0);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

//        loginActions.loginAsCustomer(customer);
//        customerActions.navigateToDeposit();
//        bankManagerActions.openAccount(customer, accounts);
//        bankManagerActions.navigateToCustomersList();
//        Assert.assertTrue(bankManagerActions.isAccountAddedToTable(customer));
    }
}
