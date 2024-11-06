package pageObjects;

import dataObjects.AddCustomerData;
import dataObjects.CustomerAccountData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerLoginPage extends BasePage{

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//select[@ng-model='custId']")
    private WebElement selectName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public void selectName(CustomerAccountData customerAccountData) {
        webElementsMethods.select(selectName, customerAccountData.getFullName());
        LoggerUtility.info("Name is selected");
    }

    public void clickOnLoginButton() {
        webElementsMethods.clickOn(loginButton);
        LoggerUtility.info("Clicked on Login button");
    }




}
