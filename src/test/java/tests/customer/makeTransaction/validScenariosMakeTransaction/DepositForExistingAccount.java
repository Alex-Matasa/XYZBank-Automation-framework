package tests.customer.makeTransaction.validScenariosMakeTransaction;

import actions.CustomerActions;
import actions.LoginActions;
import dataObjects.*;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class DepositForExistingAccount extends Hooks {

    @Test
    public void depositForExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.DEPOSIT_FOR_EXISTING_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = customer.getAccounts().get(0);
        Transactions transaction = dataModel.transactions.get(0);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(account);
        customerActions.navigateToDeposit();
        customerActions.makeTransaction(account,transaction);
        customerActions.navigateToTransactions();
        customerActions.validateTransactionsHistory(account);
    }
}
