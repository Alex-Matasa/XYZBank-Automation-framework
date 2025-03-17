package pageObjects.bankManager;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
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

        LoggerUtility.info("Clicked on Delete button on the first entry");
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Clicked on Delete button on the first entry in the table");
    }

    public void searchCustomer(String lastName) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, lastName);

        LoggerUtility.info("Entered Last Name of the customer");
    }

    public List<String> getListOfCustomers() {
        return webElementsMethods.extractDataAsStringList(CustomersLocators.customersList);
    }
}
