package userFlows;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.Accounts;
import dataObjects.Customers;
import pageObjects.PageType;

public class Flows {

    public static void addCustomer(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer) {
        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.ADD_CUSTOMER);
        bankManagerActions.addCustomer(customer);
    }

    public static void openAccount(LoginActions loginActions, BankManagerActions bankManagerActions, Customers customer, Accounts account) {
        loginActions.loginAsBankManager();
        bankManagerActions.navigateToPage(PageType.OPEN_ACCOUNT);
        bankManagerActions.openAccount(customer, account);
    }

}
