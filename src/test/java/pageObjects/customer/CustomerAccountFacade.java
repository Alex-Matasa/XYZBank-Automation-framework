package pageObjects.customer;

import dataObjects.Customers;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import pageObjects.locators.CustomerAccountFacadeLocators;
import pageObjects.locators.DepositLocators;
import pageObjects.locators.WithdrawLocators;
import validation.ActualMessages;
import validation.ExpectedMessages;
import validation.ValidationUtils;

import java.util.List;

public class CustomerAccountFacade extends BasePage {

    public CustomerAccountFacade(WebDriver driver) {
        super(driver);
    }

    private TransactionsPage transactionsPage;

    public void navigateToPage(String pageName) {

        switch (pageName) {
            case "Transactions":
                transactionsPage = new TransactionsPage(driver);
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.transactionsButton);
                LoggerUtility.info("Clicked on Transaction tab");
                break;
            case "Deposit":
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.depositButton);
                LoggerUtility.info("Clicked on Deposit tab");
                break;
            case "Withdraw":
                webElementsMethods.clickOn(CustomerAccountFacadeLocators.withdrawlButton);
                LoggerUtility.info("Clicked on Withdrawl tab");
                break;
        }
    }

    public void selectAccountId(String id) {
        webElementsMethods.selectByText(CustomerAccountFacadeLocators.selectAccountId, id);
        LoggerUtility.info("The account is selected");
    }

    public void enterAmount(String amount, String type) {
        if (amount != null) {
            if (type.equals("Credit")) webElementsMethods.sendKeys(DepositLocators.amountToBeDeposited, amount);
            else webElementsMethods.sendKeys(WithdrawLocators.amountToBeWithdrawn, amount);
            LoggerUtility.info("The amount was entered");
        }
    }

    public boolean validateNoAccountCustomerMessage() {
        return ValidationUtils.alertMessageEqualsText(ExpectedMessages.CUSTOMER_NO_ACCOUNT_MESSAGE);
    }

    public boolean validateCustomerAccountDashboard() {
        return webElementsMethods.isElementVisible(CustomerAccountFacadeLocators.transactionsButton)
                && webElementsMethods.isElementVisible(CustomerAccountFacadeLocators.depositButton)
                && webElementsMethods.isElementVisible(CustomerAccountFacadeLocators.withdrawlButton);
    }

    public boolean validateWelcomingMessage(Customers customer) {
        return driver.findElement(CustomerAccountFacadeLocators.welcome).getText().contains("Welcome " + customer.getFullName());
    }

    public void submitTransaction(String type, String amount) {
        if (type.equals("Credit")) {
            webElementsMethods.clickOn(DepositLocators.submitDepositButton);
            LoggerUtility.info("Clicked on submit deposit button");
        } else {
            webElementsMethods.clickOn(WithdrawLocators.submitWithdrawButton);
            LoggerUtility.info("Clicked on submit withdraw button");
        }

        String message = "";

        if (amount == null) {
            if (type.equals("Credit"))
                message = webElementsMethods.getAlertTextForEmptyElement(DepositLocators.amountToBeDeposited);

            else message = webElementsMethods.getAlertTextForEmptyElement(WithdrawLocators.amountToBeWithdrawn);
        } else message = webElementsMethods.getTextFromElement(CustomerAccountFacadeLocators.message);

        ActualMessages.setActualMessage(message);
    }

    public List<String> getAccountInfo() {
        return webElementsMethods.extractDataAsStringList(CustomerAccountFacadeLocators.accountInfoDisplayedList);
    }

    public void clickOnLogoutButton() {
        webElementsMethods.clickOn(CustomerAccountFacadeLocators.logoutButton);
        LoggerUtility.info("Clicked on logout button");
    }
}


