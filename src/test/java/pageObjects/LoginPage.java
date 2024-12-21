package pageObjects;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[text() = 'Bank Manager Login']")
    private WebElement bankManagerLoginButton;
    @FindBy(xpath = "//button[text() = 'Customer Login']")
    private WebElement customerLoginButton;


    public void clickOnBankManagerLogin() {
        webElementsMethods.clickOn(bankManagerLoginButton);
        LoggerUtility.info("Clicked on Bank Manager Login button");
    }

    public void clickOnCustomerLogin() {
        webElementsMethods.clickOn(customerLoginButton);
        LoggerUtility.info("Clicked on Customer Login button");
    }




}
