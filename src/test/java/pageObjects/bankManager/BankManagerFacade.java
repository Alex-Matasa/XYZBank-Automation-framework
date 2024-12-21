package pageObjects.bankManager;

import dataObjects.Accounts;
import dataObjects.Customers;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.BankManagerFacadeLocators;

import java.util.List;

public class BankManagerFacade extends BasePage {

    public BankManagerFacade(WebDriver driver) {
        super(driver);
    }

    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;

    public void navigateToPage(String pageName) {
        switch (pageName) {
            case "Add Customer" :
                addCustomerPage = new AddCustomerPage(driver);
                webElementsMethods.clickOn(BankManagerFacadeLocators.addCustomerButton);
                LoggerUtility.info("Clicked on Add Customer button");
                break;
            case "Open Account" :
                openAccountPage = new OpenAccountPage(driver);
                webElementsMethods.clickOn(BankManagerFacadeLocators.openAccountButton);
                LoggerUtility.info("Clicked on Open Account button");
                break;
            case "Customers" :
                customersPage = new CustomersPage(driver);
                webElementsMethods.clickOn(BankManagerFacadeLocators.customersButton);
                LoggerUtility.info("Clicked on Customers button");
                break;
        }
    }

    public void validateManagerDashboard() {

        List<WebElement> tabsList = List.of((driver.findElement(BankManagerFacadeLocators.addCustomerButton)),
                        driver.findElement(BankManagerFacadeLocators.openAccountButton),
                        driver.findElement(BankManagerFacadeLocators.customersButton));
        List<String> tabsListLabels = List.of("Add Customer", "Open Account", "Customers");
        Assert.assertTrue(assertionsMethods.validateText(tabsList, tabsListLabels));
        LoggerUtility.info("Tabs are displayed");
    }

    //    public void addMultipleCustomers(CustomerData customerData) {
//        navigateToPage("Add Customer");
//        addCustomerPage.fillEntireFormAndSubmit(customerData);
//    }




}

