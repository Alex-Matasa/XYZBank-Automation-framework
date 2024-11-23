package pageObjects.bankManager;

import dataObjects.CustomerAccountData;
import dataObjects.CustomerData;
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


    public void validateLastEntry(CustomerData customerData){
        String accountId = "";

        if (customerData.getAccounts() != null && !customerData.getAccounts().isEmpty()) {
            accountId = customerData.getAccounts().get(0).getAccountId();
        }

        List<String> lastCustomerAddedInfo =  List.of(customerData.getFirstName(), customerData.getLastName(), customerData.getPostCode(), accountId);
        Assert.assertTrue(assertionsMethods.validateText(this.lastCustomerAddedInfo, lastCustomerAddedInfo));
        LoggerUtility.info("Last customer is added to the Customers table with correct info");
    }

    public void deleteCustomer(CustomerData customerData) {
        searchField.sendKeys(customerData.getLastName());
        LoggerUtility.info("Entered Last Name of the customer");

        CustomerAccountData customerAccountData = customerData.getAccounts().get(0);

        List<String> list = List.of(customerData.getFirstName(), customerData.getLastName(), customerData.getPostCode(), customerAccountData.getAccountId());
        Assert.assertTrue(assertionsMethods.validateText(allCustomersInfo, list));

        if(customersList.size() == 1) {
            customersList.get(0).findElement(By.xpath(".//td/button")).click();
            LoggerUtility.info("Customer was deleted");
        }

        Assert.assertTrue(customersList.isEmpty());

    }












}
