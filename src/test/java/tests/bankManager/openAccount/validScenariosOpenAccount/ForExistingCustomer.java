package tests.bankManager.openAccount.validScenariosOpenAccount;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;
import dataObjects.ResourcePath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.bankManager.CustomersPage;
import sharedData.Hooks;

import java.util.List;

public class ForExistingCustomer extends Hooks {

    @Test
    public void openAccountForAnnExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.OPEN_ACCOUNT_FOR_AN_EXISTING_CUSTOMER_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = dataModel.getAccounts().get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToOpenAccount();
        bankManagerActions.openAccount(customer, account);
        bankManagerActions.navigateToCustomersList();

    }
}
