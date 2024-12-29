package pageObjects.bankManager;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import pageObjects.locators.CustomersLocators;

import java.util.ArrayList;
import java.util.List;

public class CustomersPage extends BasePage {

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeleteButton(WebElement customerToBeDeleted) {
        webElementsMethods.clickOn(customerToBeDeleted.findElement(CustomersLocators.deleteButton));
    }

    public void searchCustomer(String lastName) {
        webElementsMethods.sendKeys(CustomersLocators.searchField, lastName);
        LoggerUtility.info("Entered Last Name of the customer");
    }

    public List<String> getAllCustomersEntries() {
        return webElementsMethods.getData(CustomersLocators.allCustomersInfo);
    }

    public List<List<String>> getListOfCustomers() {
        List<List<String>> listOfCustomers = new ArrayList<>();
        List<WebElement> listOfCustomersWE = driver.findElements(CustomersLocators.customersList);

        for (int i = 1; i <= listOfCustomersWE.size(); i++) {
            List<String> list = new ArrayList<>();
            String dynamicXpath = CustomersLocators.customersList + "[" + i + "]" + CustomersLocators.customerInfo;
            dynamicXpath = dynamicXpath.replace("By.xpath: ", "");

            List<WebElement> webElementList = driver.findElements(By.xpath(dynamicXpath));

            for (WebElement element : webElementList) {
                list.add(element.getText());
            }

            listOfCustomers.add(list);
        }

        return listOfCustomers;
    }
}
