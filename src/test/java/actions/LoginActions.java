package actions;

import dataObjects.Customers;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPage;
import pageObjects.CustomerLoginPage;
import pageObjects.LoginPage;

public class LoginActions {

    private WebDriver driver;
    private CommonPage commonPage;
    private LoginPage loginPage;
    private CustomerLoginPage customerLoginPage;

    public LoginActions(WebDriver driver) {
        this.driver = driver;
    }

    public void loginAsBankManager() {
        commonPage = new CommonPage(driver);
        loginPage = new LoginPage(driver);

        commonPage.clickOnHomeButton();
        loginPage.clickOnBankManagerLogin();
    }

    public void loginAsCustomer(Customers customers) {
        commonPage = new CommonPage(driver);
        loginPage = new LoginPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);

        commonPage.clickOnHomeButton();
        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customers.getFullName());
        customerLoginPage.clickOnLoginButton();
    }

}
