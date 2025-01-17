package pageObjects.bankManager;


import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}

