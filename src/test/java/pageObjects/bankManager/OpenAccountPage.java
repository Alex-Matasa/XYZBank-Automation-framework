package pageObjects.bankManager;

import dataObjects.AccountData;
import dataObjects.CustomerData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pageObjects.BasePage;

public class OpenAccountPage extends BasePage {

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@name='userSelect']")
    private WebElement selectCustomer;
    @FindBy(xpath = "//select[@name='currency']")
    private WebElement selectCurrency;
    @FindBy(xpath = "//button[text()='Process']")
    private WebElement processButton;

    private String actualSuccessMessage;



    public void selectCustomer(CustomerData customerData) {
        webElementsMethods.select(selectCustomer,  customerData.getFullName());
        LoggerUtility.info("Customer is selected");
    }

    public void selectCurrency(AccountData accountData) {
        webElementsMethods.select(selectCurrency,accountData.getCurrency());
        LoggerUtility.info("Currency is selected");
    }

    public void clickOnProcessButton(CustomerData customerData) {
        webElementsMethods.clickOn(processButton);
        LoggerUtility.info("Clicked on Process button ");
        actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");
        customerData.getAccounts().get(0).setAccountId(actualSuccessMessage.split(":")[1]);
        customerData.getAccounts().get(0).setBalance("0");   }


    public void validateSuccessfulMessage() {
        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Account created successfully with account Number"));
        LoggerUtility.info("Successful message is displayed");
    }


}
