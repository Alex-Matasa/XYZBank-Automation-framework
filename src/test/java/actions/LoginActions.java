package actions;

import dataObjects.Customers;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import org.testng.Assert;
import pageObjects.locators.CustomerAccountFacadeLocators;
import validation.ActualMessages;
import validation.ValidationUtils;
import org.openqa.selenium.WebDriver;
import pageObjects.CustomerLoginPage;
import pageObjects.HomePage;

public class LoginActions {

    private WebDriver driver;
    private HomePage homePage;
    private CustomerLoginPage customerLoginPage;
    private ValidationUtils validationUtils;

    public LoginActions(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAsBankManager() {
        homePage = new HomePage(driver);

        homePage.clickOnHomePageButton();
        homePage.clickOnBankManagerLogin();
    }

    public void loginAsCustomer(Customers customer) {
        homePage = new HomePage(driver);
        customerLoginPage = new CustomerLoginPage(driver);

        homePage.clickOnHomePageButton();
        homePage.clickOnCustomerLogin();
        customerLoginPage.selectName(customer.getFullName());
        customerLoginPage.clickOnLoginButton();

        if (customer.getAccounts().isEmpty()) {
            ActualMessages.setActualMessage(driver.findElement(CustomerAccountFacadeLocators.openAccountMessage).getText());
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
