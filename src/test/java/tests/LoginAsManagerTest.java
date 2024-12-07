package tests;

import actions.LoginActions;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import sharedData.Hooks;

public class LoginAsManagerTest extends Hooks {

    @Test
    public void loginAsManager() {
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());
        LoginActions loginActions = new LoginActions(getDriver());

        loginActions.loginAsBankManager();
        bankManagerFacade.validateManagerDashboard();
    }
}
