package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import pageObjects.locators.CustomersLocators;

import java.util.List;

public class CustomersPage extends BasePage {

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeleteButton (WebElement customerToBeDeleted) {
        webElementsMethods.clickOn(customerToBeDeleted.findElement(CustomersLocators.deleteButton));
    }

    public void searchCustomer(String lastName) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, lastName);
        LoggerUtility.info("Entered Last Name of the customer");
    }

    public List <String> getAllCustomersEntries() {
        return webElementsMethods.getData(CustomersLocators.allCustomersInfo);
    }

    public List<String> getLastCustomerAdded() {
        List<String>list = webElementsMethods.getData(CustomersLocators.lastCustomerAddedInfo);
        return webElementsMethods.getData(CustomersLocators.lastCustomerAddedInfo);
    }


}
