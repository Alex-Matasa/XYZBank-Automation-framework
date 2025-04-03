package tests.loginLogout;

import actions.LoginActions;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.bankManager.BankManagerFacade;
import sharedData.Hooks;
import suites.TestSuite;

public class LoginAsBankManagerTest extends Hooks {

    @Test(groups = {TestSuite.REGRESSION_SUITE, "loginLogout"})
    public void loginAsBankManager() {
        LoginActions loginActions = new LoginActions(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());

        loginActions.loginAsBankManager();
        Assert.assertTrue(bankManagerFacade.isDashboardDisplayed());
        ExtentUtility.addTestLog(StepType.PASS_STEP, "The user is logged in");
    }
}
