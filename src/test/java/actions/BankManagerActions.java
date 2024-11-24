package actions;

import dataObjects.AccountData;
import dataObjects.CustomerData;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.AddCustomerPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.OpenAccountPage;

public class BankManagerActions {

    private WebDriver driver;
    private LoginPage loginPage;
    private BankManagerFacade bankManagerFacade;
    private CommonPage commonPage;
    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;

    public BankManagerActions(WebDriver driver) {
        this.driver = driver;
    }


    public void addCustomer(CustomerData customerData) {
        bankManagerFacade = new BankManagerFacade(driver);
        addCustomerPage = new AddCustomerPage(driver);
        bankManagerFacade.navigateToPage("Add Customer");
        addCustomerPage.fillEntireFormAndSubmit(customerData);
    }

    public void openAccount(AccountData accountData, CustomerData customerData) {
        bankManagerFacade = new BankManagerFacade(driver);
        openAccountPage = new OpenAccountPage(driver);
        bankManagerFacade.navigateToPage("Open Account");
        openAccountPage.selectCustomer(customerData);
        openAccountPage.selectCurrency(accountData);
        openAccountPage.clickOnProcessButton(customerData);
    }

}
