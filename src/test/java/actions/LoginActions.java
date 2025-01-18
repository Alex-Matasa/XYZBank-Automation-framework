package actions;

import dataObjects.Customers;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import helperMethods.AssertionsMethods;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;
import pageObjects.locators.CustomerAccountFacadeLocators;

public class LoginActions {

    private WebDriver driver;
    private CommonPage commonPage;
    private LoginPage loginPage;
    private CustomerLoginPage customerLoginPage;
    private AssertionsMethods assertionsMethods;

    public LoginActions(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAsBankManager() {
        loginPage = new LoginPage(driver);

        loginPage.clickOnBankManagerLogin();

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Logged in as Bank Manager");
    }

    public void loginAsCustomer(Customers customer) {
        loginPage = new LoginPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
        assertionsMethods = new AssertionsMethods(driver);

        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customer.getFullName());
        customerLoginPage.clickOnLoginButton();

        Assert.assertTrue(assertionsMethods.actualEqualExpected(CustomerAccountFacadeLocators.welcome, customer.getFullName()));

        LoggerUtility.info("Logged in as customer");

        ExtentUtility.addTestLog(StepType.INFO_STEP, "Logged in as Customer");
    }

}
