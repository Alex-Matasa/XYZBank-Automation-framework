package pageObjects;

import dataObjects.CustomerData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerLoginPage extends BasePage{

    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//select[@name='userSelect']")
    private WebElement selectName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public void selectName(CustomerData customerData) {
        webElementsMethods.select(selectName, customerData.getFullName());
        LoggerUtility.info("Name is selected");
    }

    public void clickOnLoginButton() {
        webElementsMethods.clickOn(loginButton);
        LoggerUtility.info("Clicked on Login button");
    }




}
