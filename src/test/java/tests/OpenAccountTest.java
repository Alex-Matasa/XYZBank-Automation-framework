package tests;

import actions.BankManagerActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import dataObjects.DataModel;

import org.testng.annotations.Test;
import sharedData.Hooks;

public class OpenAccountTest extends Hooks {

    @Test
    public void openAccount() {

        DataModel dataModel = new DataModel("src/test/resources/testData/OpenAccount.json");
        Customers customers = dataModel.customers.get(0);
        Accounts accounts = dataModel.getAccounts().get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        bankManagerActions.openAccountForExistingCustomer(accounts, customers);
    }




}
