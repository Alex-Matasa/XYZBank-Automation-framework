package pageObjects.bankManager;


import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;

import pageObjects.BasePage;
import pageObjects.PageType;
import pageObjects.locators.BankManagerFacadeLocators;


public class BankManagerFacade extends BasePage {

    public BankManagerFacade(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(String pageName) {
        switch (pageName) {
            case PageType.ADD_CUSTOMER :
                webElementsMethods.clickOn(BankManagerFacadeLocators.addCustomerButton);
                break;

            case PageType.OPEN_ACCOUNT:
                webElementsMethods.clickOn(BankManagerFacadeLocators.openAccountButton);

                break;
            case PageType.CUSTOMERS:
                webElementsMethods.clickOn(BankManagerFacadeLocators.customersButton);
                break;
        }

        LoggerUtility.info("Clicked on " + pageName + " button");
    }

    public boolean isDashboardDisplayed() {
        return webElementsMethods.isElementVisible(BankManagerFacadeLocators.addCustomerButton)
                && webElementsMethods.isElementVisible(BankManagerFacadeLocators.openAccountButton)
                && webElementsMethods.isElementVisible(BankManagerFacadeLocators.customersButton);
    }
}

