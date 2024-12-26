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
        loginPage = new LoginPage(driver);

        loginPage.clickOnBankManagerLogin();
    }

    public void loginAsCustomer(Customers customers) {
        loginPage = new LoginPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);

        loginPage.clickOnCustomerLogin();
        customerLoginPage.selectName(customers.getFullName());
        customerLoginPage.clickOnLoginButton();
    }

}
