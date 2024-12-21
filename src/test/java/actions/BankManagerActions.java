package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPage;
import pageObjects.LoginPage;
import pageObjects.bankManager.AddCustomerPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.CustomersPage;
import pageObjects.bankManager.OpenAccountPage;

public class BankManagerActions {

    private WebDriver driver;

    private BankManagerFacade bankManagerFacade;
    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;

    public BankManagerActions(WebDriver driver) {
        this.driver = driver;
    }

    public void addCustomer(Customers customers) {
        bankManagerFacade = new BankManagerFacade(driver);
        addCustomerPage = new AddCustomerPage(driver);

        bankManagerFacade.navigateToPage("Add Customer");
        addCustomerPage.enterFirstName(customers.getFirstName());
        addCustomerPage.enterLastName(customers.getLastName());
        addCustomerPage.enterPostCode(customers.getPostCode());
        customers.setCustomerId(addCustomerPage.clickOnSubmitButton());
    }

    public void openAccountForExistingCustomer(Accounts accounts, Customers customers) {
        bankManagerFacade = new BankManagerFacade(driver);
        openAccountPage = new OpenAccountPage(driver);
        Accounts newAccount = new Accounts();

        bankManagerFacade.navigateToPage("Open Account");
        openAccountPage.selectCustomer(customers.getFullName());
        openAccountPage.selectCurrency(accounts.getCurrency());
        customers.getAccounts().add(newAccount);
        customers.getAccounts().get(0).setAccountId(openAccountPage.clickOnProcessButton());
        customers.getAccounts().get(0).setBalance("0");
    }

    public void deleteCustomer(Customers customers) {
        bankManagerFacade = new BankManagerFacade(driver);
        customersPage = new CustomersPage(driver);

        bankManagerFacade.navigateToPage("Customers");
        customersPage.deleteCustomer(customers);
    }

    public void validateCustomer(Customers customers) {
        bankManagerFacade = new BankManagerFacade(driver);
        customersPage = new CustomersPage(driver);

        bankManagerFacade.navigateToPage("Customers");
        customersPage.validateLastEntry(customers);
    }

}
