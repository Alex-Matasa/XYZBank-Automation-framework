package pageObjects.bankManager;

import dataObjects.CustomerData;
import dataObjects.InputAccountData;
import dataObjects.InputCustomerData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

import java.util.List;

public class BankManagerFacade extends BasePage {

    public BankManagerFacade(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private WebElement addCustomerButton;
    @FindBy(xpath = "//button[@ng-click='openAccount()']")
    private WebElement openAccountButton;
    @FindBy(xpath = "//button[@ng-click='showCust()']")
    private WebElement customersButton;

    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;

    public void navigateToPage(String pageName) {
        switch (pageName) {
            case "Add Customer" :
                addCustomerPage = new AddCustomerPage(driver);
                webElementsMethods.clickOn(addCustomerButton);
                LoggerUtility.info("Clicked on Add Customer button");
                break;
            case "Open Account" :
                openAccountPage = new OpenAccountPage(driver);
                webElementsMethods.clickOn(openAccountButton);
                LoggerUtility.info("Clicked on Open Account button");
                break;
            case "Customers" :
                customersPage = new CustomersPage(driver);
                webElementsMethods.clickOn(customersButton);
                LoggerUtility.info("Clicked on Customers button");
                break;
        }
    }

    public void addCustomer(InputCustomerData inputCustomerData, CustomerData customerData) {
        navigateToPage("Add Customer");
        addCustomerPage.fillEntireFormAndSubmit(inputCustomerData,customerData);
    }

    public void addMultipleCustomers(InputCustomerData inputCustomerData, CustomerData customerData) {
        navigateToPage("Add Customer");
        addCustomerPage.fillEntireFormAndSubmit(inputCustomerData,customerData);
    }

    public void openAccount(InputAccountData inputAccountData, CustomerData customerData) {
        navigateToPage("Open Account");
        openAccountPage.openNewAccount(inputAccountData, customerData );
        navigateToPage("Customers");
    }

    public void deleteCustomer(CustomerData customerData) {
        navigateToPage("Customers");
        customersPage.deleteCustomer(customerData);
    }

    public void validateCustomer(CustomerData customerData) {
        navigateToPage("Customers");
        customersPage.validateLastEntry(customerData);
    }

    public void validateManagerDashboard() {
        List<WebElement> tabsList = List.of(addCustomerButton, openAccountButton, customersButton);
        List<String> tabsListLabels = List.of("Add Customer", "Open Account", "Customers");
        Assert.assertTrue(assertionsMethods.validateText(tabsList, tabsListLabels));
        LoggerUtility.info("Tabs are displayed");
    }

    public void validateLastEntryData(CustomerData customerData) {
        customersPage.validateLastEntry(customerData);
    }







}

