package pageObjects.bankManager;

import dataObjects.Accounts;
import dataObjects.Customers;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

import java.util.List;

public class CustomersPage extends BasePage {

    public CustomersPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//tbody/tr[last()]//*[contains(@class, 'ng-binding')]")
    private List<WebElement> lastCustomerAddedInfo;
    @FindBy(xpath = "//tbody/tr//*[contains(@class, 'ng-binding')]")
    private List<WebElement> allCustomersInfo;
    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> customersList;
    @FindBy(xpath = "//button[@class='btn home']")
    private WebElement homeButton;
    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    private WebElement searchField;


    public void validateLastEntry(Customers customers){
        String accountId = "";

        if (customers.getAccounts() != null && !customers.getAccounts().isEmpty()) {
            accountId = customers.getAccounts().get(0).getAccountId();
        }

        List<String> lastCustomerAddedInfo =  List.of(customers.getFirstName(), customers.getLastName(), customers.getPostCode(), accountId);
        Assert.assertTrue(assertionsMethods.validateText(this.lastCustomerAddedInfo, lastCustomerAddedInfo));
        LoggerUtility.info("Last customer is added to the Customers table with correct info");
    }

    public void deleteCustomer(Customers customers) {
        searchField.sendKeys(customers.getLastName());
        LoggerUtility.info("Entered Last Name of the customer");

        Accounts accounts = customers.getAccounts().get(0);

        List<String> list = List.of(customers.getFirstName(), customers.getLastName(), customers.getPostCode(), accounts.getAccountId());
        Assert.assertTrue(assertionsMethods.validateText(allCustomersInfo, list));

        if(customersList.size() == 1) {
            customersList.get(0).findElement(By.xpath(".//td/button")).click();
            LoggerUtility.info("Customer was deleted");
        }

        Assert.assertTrue(customersList.isEmpty());

    }












}
