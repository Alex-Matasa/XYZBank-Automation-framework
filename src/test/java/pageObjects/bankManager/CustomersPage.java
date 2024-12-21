package pageObjects.bankManager;

import dataObjects.Accounts;
import dataObjects.Customers;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.CustomersLocators;

import java.util.List;

public class CustomersPage extends BasePage {

    public CustomersPage(WebDriver driver) {
        super(driver);
    }


    public void validateLastEntry(Customers customers) {
        String accountId = "";

        if (customers.getAccounts() != null && !customers.getAccounts().isEmpty()) {
            accountId = customers.getAccounts().get(0).getAccountId();
        }

        List<String> lastCustomerAddedInfo = List.of(customers.getFirstName(), customers.getLastName(), customers.getPostCode(), accountId);
        Assert.assertTrue(assertionsMethods.validateText(CustomersLocators.lastCustomerAddedInfo, lastCustomerAddedInfo));
        LoggerUtility.info("Last customer is added to the Customers table with correct info");
    }

    public void deleteCustomer(Customers customers) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, customers.getLastName());
        LoggerUtility.info("Entered Last Name of the customer");

        Accounts accounts = customers.getAccounts().get(0);

        List<String> list = List.of(customers.getFirstName(), customers.getLastName(), customers.getPostCode(), accounts.getAccountId());
        Assert.assertTrue(assertionsMethods.validateText(CustomersLocators.allCustomersInfo, list));
        List<WebElement> customersList = driver.findElements(CustomersLocators.customersList);

        if (customersList.size() == 1) {
            customersList.get(0).findElement(By.xpath(".//td/button")).click();
            LoggerUtility.info("Customer was deleted");
        }

        Assert.assertTrue(customersList.isEmpty());

    }


}
