package pageObjects.customer;

import helperMethods.UtilityMethods;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.BasePage;
import pageObjects.locators.DepositLocators;

public class DepositPage extends BasePage {

    public DepositPage(WebDriver driver) {
        super(driver);
    }

    public void enterAmount(String amount) {
        if(amount != null) {
            webElementsMethods.sendKeys(DepositLocators.amount, amount);
            LoggerUtility.info(amount + " entered to deposit");
        }

    }

    public void clickOnDeposit(String amount) {

        webElementsMethods.clickOn(DepositLocators.depositSubmitButton);
        LoggerUtility.info("Clicked on deposit button");

        if (amount == null) {
            WebElement elementField = driver.findElement(DepositLocators.amount);
            Assert.assertTrue(assertionsMethods.validateText((String) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].validationMessage;", elementField), "Please fill out this field."));

            LoggerUtility.info("Warning alert message was displayed.");
        } else if (UtilityMethods.parseStringToInt(amount) >= 0) {
            Assert.assertTrue(assertionsMethods.validateText(DepositLocators.message, "Deposit Successful"));

            LoggerUtility.info("Successful alert message was displayed");
        }

    }








}

