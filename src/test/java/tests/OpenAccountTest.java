package tests;

import actions.BankManagerActions;
import actions.LoginActions;
import dataObjects.AccountData;
import dataObjects.CustomerData;
import dataObjects.DataModel;

import org.testng.annotations.Test;
import pageObjects.bankManager.OpenAccountPage;
import sharedData.Hooks;

public class OpenAccountTest extends Hooks {

    @Test
    public void openAccount() {

        DataModel dataModel = new DataModel("src/test/resources/testData/OpenAccount.json");
        CustomerData customerData = dataModel.customerData.get(0);
        AccountData accountData = dataModel.getAccountData().get(0);
        BankManagerActions bankManagerActions = new BankManagerActions(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());
        OpenAccountPage openAccountPage = new OpenAccountPage(getDriver());

        loginActions.loginAsBankManager();
        bankManagerActions.openAccount(accountData, customerData);
        openAccountPage.validateSuccessfulMessage();

    }




}
