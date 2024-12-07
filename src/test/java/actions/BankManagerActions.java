package actions;

import dataObjects.AccountData;
import dataObjects.CustomerData;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.BankManagerFacade;

public class BankManagerActions {

    private WebDriver driver;
    private LoginPage loginPage;
    private BankManagerFacade bankManagerFacade;
    private CommonPage commonPage;

    public BankManagerActions(WebDriver driver) {
        this.driver = driver;
    }

    public void addNewCustomer(CustomerData customerData) {

        loginPage = new LoginPage(driver);
        bankManagerFacade = new BankManagerFacade(driver);
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade.addCustomer(customerData);
    }

    public void openAccountForExistingCustomer(AccountData accountData, CustomerData customerData) {
        commonPage = new CommonPage(driver);
        commonPage.clickOnHomeButton();
        loginPage = new LoginPage(driver);
        loginPage.clickOnBankManagerLogin();
        bankManagerFacade = new BankManagerFacade(driver);
        bankManagerFacade.openAccount(accountData,customerData);
    }

}
