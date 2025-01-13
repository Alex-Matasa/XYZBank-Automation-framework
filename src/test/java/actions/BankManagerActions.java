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

import java.util.ArrayList;
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


    public void navigateToAddCustomer() {
        bankManagerFacade = new BankManagerFacade(driver);
        bankManagerFacade.navigateToPage("Add Customer");
    }

    public void navigateToOpenAccount() {
        bankManagerFacade = new BankManagerFacade(driver);
        bankManagerFacade.navigateToPage("Open Account");
    }

    public void navigateToCustomersList() {
        bankManagerFacade = new BankManagerFacade(driver);
        bankManagerFacade.navigateToPage("Customers");
    }

    public void addCustomer(Customers customers) {
        bankManagerFacade = new BankManagerFacade(driver);
        addCustomerPage = new AddCustomerPage(driver);

        addCustomerPage.enterFirstName(customers.getFirstName());
        addCustomerPage.enterLastName(customers.getLastName());
        addCustomerPage.enterPostCode(customers.getPostCode());
        customers.setCustomerId(addCustomerPage.clickOnSubmitButton(customers.getFirstName(), customers.getLastName(), customers.getPostCode()));
    }

    public void openAccount(Customers customer, Accounts account) {
        openAccountPage = new OpenAccountPage(driver);
        Accounts newAccount = new Accounts();

        openAccountPage.selectCustomer(customer.getFullName());
        openAccountPage.selectCurrency(account.getCurrency());
        customer.getAccounts().add(newAccount);
        customer.getAccounts().get(0).setAccountId(openAccountPage.clickOnProcessButton());
        customer.getAccounts().get(0).setCurrency(account.getCurrency());
        customer.getAccounts().get(0).setBalance("0");
    }

    public void deleteCustomer(Customers customers) {
        customersPage = new CustomersPage(driver);

        customersPage.searchCustomer(customers.getLastName());

        List<WebElement> customersList = driver.findElements(CustomersLocators.customersList);

        if (customersList.size() == 1) {
            customersList.get(0).findElement(By.xpath(".//td/button")).click();
            LoggerUtility.info("Customer was deleted");
        }

        Assert.assertTrue(customersList.isEmpty());
    }

    public boolean isCustomerInTheList(Customers customers) {
        boolean isCustomerInTheList = false;

        if (customers.getCustomerId() != null) {
            customersPage = new CustomersPage(driver);
            assertionsMethods = new AssertionsMethods(driver);

            List<String> customerAdded = List.of(customers.getFirstName(), customers.getLastName(),
                    customers.getPostCode());
            List<String> actualList = customersPage.getListOfCustomers();

            for (int i = 0; i < actualList.size(); i++) {
                if (actualList.get(i).contains(customers.getFullName())) {
                    boolean allFieldsMatch = true;
                    for (int j = 0; j < customerAdded.size(); j++) {
                        if(!assertionsMethods.validatePartialText(actualList.get(i), customerAdded.get(j))) {
                            allFieldsMatch = false;
                            break;
                        }
                    }
                    if (allFieldsMatch) {
                        isCustomerInTheList = true;
                        break;
                    }
                }
                if (isCustomerInTheList) break;
            }

            if (isCustomerInTheList) LoggerUtility.info("The Customer is added to the list");
            else LoggerUtility.info(("The Customer is not added to the list"));
        }

        return isCustomerInTheList;
    }

    public boolean isCustomerDuplicated(Customers customers) {
        customersPage = new CustomersPage(driver);
        assertionsMethods = new AssertionsMethods(driver);

        List<String> actualList = customersPage.getListOfCustomers();

        boolean isCustomerDuplicated = false;
        int count = 0;

        for (int i = 0; i < actualList.size(); i++) {

            if (actualList.get(i).contains(customers.getFullName()) && actualList.get(i).contains(customers.getPostCode())) {
                count++;
            }
        }

        if (count == 2) {
            isCustomerDuplicated = true;
        }

        if (!isCustomerDuplicated) LoggerUtility.info("The Customer is not duplicated");

        return isCustomerDuplicated;
    }

    public boolean isAccountAddedToTheList(Customers customer) {
        customersPage = new CustomersPage(driver);
        assertionsMethods = new AssertionsMethods(driver);
        boolean isAccountAdded = false;

        List<String> actualList = customersPage.getListOfCustomers();

        for (int i = 0; i < actualList.size(); i++) {

            if (actualList.get(i).contains(customer.getFullName())) {
                assertionsMethods.validatePartialText(customer.getAccounts().get(0).getAccountId(), actualList.get(i));
                isAccountAdded = true;
                break;
            }
        }

        return isAccountAdded;
    }


}
