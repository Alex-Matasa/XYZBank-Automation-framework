package tests;

import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;
import sharedData.Hooks;

public class LoginAsManagerTest extends Hooks {

    @Test
    public void loginAsManager() {
        LoginPage loginPage = new LoginPage(getDriver());
        BankManagerFacade bankManagerFacade = new BankManagerFacade(getDriver());

        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.validateManagerDashboard();

    }
}
