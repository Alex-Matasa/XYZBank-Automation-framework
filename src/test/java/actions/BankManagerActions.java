package actions;

import dataObjects.Accounts;
import dataObjects.Customers;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
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

    private final WebDriver driver;

    private BankManagerFacade bankManagerFacade;
    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;

    public BankManagerActions(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateToPage(String pageName) {
        bankManagerFacade = new BankManagerFacade(driver);

        bankManagerFacade.navigateToPage(pageName);

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Navigated to " + pageName);
    }

    public void addCustomer(Customers customers) {
        bankManagerFacade = new BankManagerFacade(driver);
        addCustomerPage = new AddCustomerPage(driver);

        addCustomerPage.enterFirstName(customers.getFirstName());
        addCustomerPage.enterLastName(customers.getLastName());
        addCustomerPage.enterPostCode(customers.getPostCode());

        customers.setCustomerId(addCustomerPage.clickOnSubmitButton(customers.getFirstName(), customers.getLastName(), customers.getPostCode()));

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Filled form and submitted");
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

    public void openAccount(Customers customer, Accounts account) {
        openAccountPage = new OpenAccountPage(driver);

        openAccountPage.selectCustomer(customer.getFullName());
        openAccountPage.selectCurrency(account.getCurrency());
        account.setAccountId(openAccountPage.clickOnProcessButton(customer.getFullName(), account.getCurrency()));

        Accounts customerAccount = new Accounts();

        customerAccount.setBalance(account.getBalance());
        customerAccount.setCurrency(account.getCurrency());
        customerAccount.setAccountId(account.getAccountId());

        customer.getAccounts().add(customerAccount);

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Account was created");
    }

    public boolean isCustomerInTheList(Customers customer) {
        boolean isCustomerInTheList = false;

        if (customer.getCustomerId() != null) {
            customersPage = new CustomersPage(driver);

            String expectedCustomer = String.join(" ", customer.getFullName(), customer.getPostCode());
            List<String> actualList = customersPage.getListOfCustomers();

            for (String actual : actualList) {
                if (actual.contains(expectedCustomer)) {
                    isCustomerInTheList = true;
                    break;
                }
            }
        }

        if (isCustomerInTheList) LoggerUtility.info("The Customer is added to the list");
        else LoggerUtility.info(("The Customer is not added to the list"));

        return isCustomerInTheList;
    }

    public boolean isCustomerDuplicated(Customers customer) {
        customersPage = new CustomersPage(driver);

        List<String> actualList = customersPage.getListOfCustomers();
        String expectedCustomer = String.join(" ", customer.getFullName(), customer.getPostCode());

        int count = 0;

        for (String actual : actualList) {
            if (actual.contains(expectedCustomer)) {
                count++;
            }
            if (count == 2) {
                LoggerUtility.info("The Customer is duplicated");
                ExtentUtility.addTestLog(StepType.FAIL_STEP, "Customer is duplicated");

                return true;
            }
        }

        LoggerUtility.info("The Customer is not duplicated");

        return false;
    }

    public boolean isAccountAddedToTheList(Customers customer) {
        customersPage = new CustomersPage(driver);

        boolean isAccountAdded = false;

        List<String> actualList = customersPage.getListOfCustomers();

        for (String actual : actualList) {
            if (actual.contains(customer.getFullName())) {
                isAccountAdded = actual.contains(customer.getAccounts().get(0).getAccountId());
                if (isAccountAdded) {
                    LoggerUtility.info("The account id is added to table");
                    break;
                }
            }
        }

        return isAccountAdded;
    }

    public boolean isAccountCreated(Accounts account) {
        return account.getAccountId().isEmpty();
    }
}
