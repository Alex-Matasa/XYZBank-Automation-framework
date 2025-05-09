package userFlows;

import dataObjects.Transactions;
import userActions.BankManagerActions;
import userActions.CustomerActions;
import userActions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import pageObjects.PageType;

public class TestPreconditions {

    public static void navigateToAddCustomerPage(LoginActions loginActions, BankManagerActions bankManagerActions) {
        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
    }

    public static void forOpeningAccount(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
    }

    public static void navigateToOpenAccountPage(LoginActions loginActions, BankManagerActions bankManagerActions) {
        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
    }

    public static void forDeletingCustomer(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
    }

    public static void forSearchingCustomer(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer, Accounts account) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
        bankManagerActions.openAccount(customer, account);
        bankManagerActions.navigateToPage(PageType.CUSTOMERS);
    }

    public static void forLogInAsCustomer(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer, Accounts account) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        Flows.openAccount(loginActions, bankManagerActions, customer, account);
    }

    public static void forLogOutAsCustomer(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer, Accounts account) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        Flows.openAccount(loginActions, bankManagerActions, customer, account);
        loginActions.loginAsCustomer(customer);
    }

    public static void forMakingATransaction(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer, Accounts account) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        Flows.openAccount(loginActions, bankManagerActions, customer, account);
        loginActions.loginAsCustomer(customer);
    }

    public static void forValidatingTransactionsTable(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer, CustomerActions customerActions, Accounts account, Transactions transaction1, Transactions transaction2) {
        Flows.addCustomer(loginActions, bankManagerActions, customer);
        Flows.openAccount(loginActions, bankManagerActions, customer, account);
        loginActions.loginAsCustomer(customer);
        customerActions.depositMoney(account, transaction1);
        customerActions.withdrawMoney(account, transaction2);
    }


}
