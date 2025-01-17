package pageObjects.bankManager;


import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.BasePage;
import pageObjects.PageType;
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
            case PageType.ADD_CUSTOMER :
                addCustomerPage = new AddCustomerPage(driver);
                webElementsMethods.clickOn(BankManagerFacadeLocators.addCustomerButton);
                break;

            case PageType.OPEN_ACCOUNT:
                openAccountPage = new OpenAccountPage(driver);
                webElementsMethods.clickOn(BankManagerFacadeLocators.openAccountButton);

                break;
            case PageType.CUSTOMERS:
                customersPage = new CustomersPage(driver);
                webElementsMethods.clickOn(BankManagerFacadeLocators.customersButton);
                break;
        }

        LoggerUtility.info("Clicked on " + pageName + "button");
    }
}

