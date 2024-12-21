package pageObjects.bankManager;

import dataObjects.Accounts;
import dataObjects.Customers;
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


    public void selectCustomer(String fullName) {
        webElementsMethods.select(selectCustomer, fullName);
        LoggerUtility.info("Customer is selected");
    }

    public void selectCurrency(String currency) {
        webElementsMethods.select(selectCurrency, currency);
        LoggerUtility.info("Currency is selected");
    }

    public String clickOnProcessButton() {
        webElementsMethods.clickOn(processButton);
        LoggerUtility.info("Clicked on Process button ");
        String actualSuccessMessage = alertsMethods.getAlertsTextAndAccept();
        LoggerUtility.info("Accepted pop-up alert");

        Assert.assertTrue(assertionsMethods.validatePartialText(actualSuccessMessage, "Account created successfully with account Number"));
        LoggerUtility.info("Account is created successfully");

        return actualSuccessMessage.split(":")[1];
    }


}
