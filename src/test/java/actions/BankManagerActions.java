package actions;

import dataObjects.AccountData;
import dataObjects.CustomerData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.CommonPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.AddCustomerPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.OpenAccountPage;

public class BankManagerActions {

    private WebDriver driver;
    private LoginPage loginPage;
    private BankManagerFacade bankManagerFacade;
    private AddCustomerPage addCustomerPage;
    private CommonPage commonPage;
    private OpenAccountPage openAccountPage;

    public BankManagerActions(WebDriver driver) {
        this.driver = driver;
    }

    public void addCustomer(CustomerData customerData) {
        bankManagerFacade = new BankManagerFacade(driver);
        addCustomerPage = new AddCustomerPage(driver);

        bankManagerFacade.navigateToPage("Add Customer");
        addCustomerPage.enterFirstName(customerData.getFirstName());
        addCustomerPage.enterLastName(customerData.getLastName());
        addCustomerPage.enterPostCode(customerData.getPostCode());
        customerData.setCustomerId(addCustomerPage.clickOnSubmitButton());
    }

    public void openAccountForExistingCustomer(AccountData accountData, CustomerData customerData) {
        bankManagerFacade = new BankManagerFacade(driver);
        openAccountPage = new OpenAccountPage(driver);

        bankManagerFacade.navigateToPage("Open Account");
        openAccountPage.selectCustomer(customerData.getFullName());
        openAccountPage.selectCurrency(accountData.getCurrency());

        AccountData newAccount = new AccountData();
        customerData.getAccounts().add(newAccount);

        customerData.getAccounts().get(0).setBalance("0");
    }

}
