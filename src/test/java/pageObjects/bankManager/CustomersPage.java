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

    public void clickOnDeleteButton() {
        webElementsMethods.clickOn(CustomersLocators.firstDeleteButton);
        LoggerUtility.info("Clicked on Delete button");
    }

    public void searchCustomer(String lastName) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, lastName);

        LoggerUtility.info("Entered Last Name of the customer");
    }

    public List<String> getListOfCustomers() {
        return webElementsMethods.extractDataAsStringList(CustomersLocators.customersList);
    }
}
