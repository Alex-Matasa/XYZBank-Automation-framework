package tests.transactions.valid;

import dataObjects.*;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PageType;
import sharedData.Hooks;
import suites.TestSuite;
import userActions.BankManagerActions;
import userActions.CustomerActions;
import userActions.LoginActions;
import userFlows.Flows;
import userFlows.TestPreconditions;
import validation.ExpectedMessages;
import validation.ValidationUtils;

public class MakeTransactionsForAllAccountsTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "makeTransactions", "validMakeTransactions"})
    public void makeTransactionsForAllAccounts() {
        DataModel dataModel = new DataModel(ResourcePath.MAKE_TRANSACTIONS_FOR_ALL_ACCOUNTS_DATA);
        Customers customer = dataModel.customers.get(0);
        Transactions transaction1 = dataModel.transactions.get(0);
        Transactions transaction2 = dataModel.transactions.get(1);
        CustomerActions customerActions = new CustomerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer);

        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
        for (int i = 0; i < dataModel.accounts.size(); i++) {
            Accounts account = dataModel.accounts.get(i);
            bankManagerActions.openAccount(customer,account);
        }

        loginActions.loginAsCustomer(customer);

        for (int i = 0; i < customer.getAccounts().size(); i++) {
            customerActions.selectAnAccount(customer.getAccounts().get(i));
            customerActions.depositMoney(customer.getAccounts().get(i), transaction1);
            Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.DEPOSIT_SUCCESSFUL_MESSAGE));
            customerActions.withdrawMoney(customer.getAccounts().get(i), transaction2);
            Assert.assertTrue(ValidationUtils.alertMessageEqualsText(ExpectedMessages.WITHDRAW_SUCCESSFUL_MESSAGE));
            Assert.assertTrue(customerActions.validateAccountInfo(customer.getAccounts().get(i)));
        }

        ExtentUtility.addTestLog(StepType.PASS_STEP, "Successful transactions for all the accounts");
    }
}
