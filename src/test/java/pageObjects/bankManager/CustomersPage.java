package pageObjects.bankManager;

import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import pageObjects.locators.CustomersLocators;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersPage extends BasePage {

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnFirstNameButton() {
        webElementsMethods.clickOn(CustomersLocators.firstNameButton);
    }

    public void clickOnLastNameButton() {
        webElementsMethods.clickOn(CustomersLocators.lastNameButton);
    }

    public void clickOnPostCodeButton() {
        webElementsMethods.clickOn(CustomersLocators.postCodeButton);
    }

    public void clickOnDeleteButton() {
        webElementsMethods.clickOn(CustomersLocators.deleteButton);

        LoggerUtility.info("Clicked on Delete button");
    }

    public void clickOnSearchButton(String string) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, string);
        LoggerUtility.info("Clicked on search bar");
    }


    public List<String> getListOfCustomers() {
        List<String> originalList =  webElementsMethods.extractDataAsStringList(CustomersLocators.customersList);

        return originalList.stream()
                .map(str -> str.replace("Delete", "").trim())  // Remove "Delete" and trim the string
                .collect(Collectors.toList());
    }

    public List<String> getListOfCustomersLowerCase() {
        List<String> customers = getListOfCustomers();

        return customers.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
