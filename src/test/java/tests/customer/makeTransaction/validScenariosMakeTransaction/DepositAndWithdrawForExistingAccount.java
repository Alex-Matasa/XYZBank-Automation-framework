package tests.customer.makeTransaction.validScenariosMakeTransaction;

import actions.CustomerActions;
import actions.LoginActions;
import dataObjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.Hooks;

public class DepositAndWithdrawForExistingAccount extends Hooks {

    @Test
    public void depositAndWithdrawForExistingCustomer() {
        DataModel dataModel = new DataModel(ResourcePath.DEPOSIT_AND_WITHDRAW_FOR_EXISTING_ACCOUNT_DATA);
        Customers customer = dataModel.customers.get(0);
        Accounts account = customer.getAccounts().get(0);
        Transactions depositTransaction = dataModel.transactions.get(0);
        Transactions withdrawTransaction = dataModel.transactions.get(1);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsCustomer(customer);
        customerActions.selectAnAccount(account);
        customerActions.navigateToDeposit();
        customerActions.makeTransaction(account,depositTransaction);
        customerActions.navigateToWithdraw();
        customerActions.makeTransaction(account,withdrawTransaction);
        customerActions.navigateToTransactions();
        System.out.println(account.getTransactions().get(0));
        Assert.assertTrue(customerActions.validateTransactionsHistory(account));
    }
}
