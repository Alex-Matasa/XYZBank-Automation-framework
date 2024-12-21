package pageObjects;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.locators.CommonLocators;

public class CommonPage extends BasePage{

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnHomeButton() {
        webElementsMethods.clickOn(CommonLocators.homeButton);
        LoggerUtility.info("Clicked on Home Button");
    }

}
