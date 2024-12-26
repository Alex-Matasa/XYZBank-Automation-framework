package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import helperMethods.AssertionsMethods;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.bankManager.AddCustomerPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.CustomersPage;
import pageObjects.bankManager.OpenAccountPage;
import pageObjects.locators.CustomersLocators;

import java.util.List;

public class BankManagerActions {

    private WebDriver driver;

    private BankManagerFacade bankManagerFacade;
    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;
    private AssertionsMethods assertionsMethods;

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
        customersPage.searchCustomer(customers.getLastName());


        List<WebElement> customersList = driver.findElements(CustomersLocators.customersList);

        if (customersList.size() == 1) {
            customersList.get(0).findElement(By.xpath(".//td/button")).click();
            LoggerUtility.info("Customer was deleted");
        }

        Assert.assertTrue(customersList.isEmpty());
    }

    public boolean validateCustomer(Customers customers) {
        bankManagerFacade  = new BankManagerFacade(driver);
        assertionsMethods = new AssertionsMethods(driver);
        customersPage = new CustomersPage(driver);
        List<String> list = List.of(customers.getFirstName(), customers.getLastName(),
                customers.getPostCode(), customers.getAccounts().get(0).getAccountId());

        bankManagerFacade.navigateToPage("Customers");
        return assertionsMethods.validateText(customersPage.getLastCustomerAdded(), list);
    }



}
