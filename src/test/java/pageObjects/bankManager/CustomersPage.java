package pageObjects.bankManager;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import pageObjects.locators.CustomersLocators;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersPage extends BasePage {

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeleteButton() {
        webElementsMethods.clickOn(CustomersLocators.firstDeleteButton);

        LoggerUtility.info("Clicked on Delete button on the first entry");
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Clicked on Delete button on the first entry in the table");
    }

    public void searchCustomer(String string) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, string);
        LoggerUtility.info("Clicked on search bar");
    }


    public List<String> getListOfCustomers() {
        List<String> originalList =  webElementsMethods.extractDataAsStringList(CustomersLocators.customersList);

        return originalList.stream()
                .map(str -> str.replace("Delete", "").trim())  // Remove "Delete" and trim the string
                .collect(Collectors.toList());
    }
}
