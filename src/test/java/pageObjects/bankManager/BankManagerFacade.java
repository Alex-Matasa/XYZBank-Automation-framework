package pageObjects.bankManager;

import dataObjects.CustomerData;
import dataObjects.AccountData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage;

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
    @FindBy(xpath = "//button[@class='btn home']")
    private WebElement homeButton;


    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;
    private String addCustomer = "Add Customer";
    private String openAccount = "Open Account";
    private String customers = "Customers";

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

    public void addCustomer(CustomerData customerData) {
        navigateToPage(addCustomer);
        addCustomerPage.fillEntireFormAndSubmit(customerData);
    }

    public void openAccount(CustomerData customerData) {
        navigateToPage(openAccount);
        openAccountPage.openNewAccount(customerData.getAccounts().get(0), customerData );
        navigateToPage(customers);
        customersPage.validateLastEntry(customerData);
    }

    public void clickOnHomeButton() {
        webElementsMethods.clickOn(homeButton);
        LoggerUtility.info("Clicked on Home button");
    }

    public void deleteCustomer(CustomerData customerData) {
        navigateToPage(customers);
        customersPage.deleteCustomer(customerData);
    }







}

