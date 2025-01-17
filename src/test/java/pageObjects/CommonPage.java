package pageObjects;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import pageObjects.locators.CommonLocators;

public class CommonPage extends BasePage {

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnHomeButton() {
        webElementsMethods.clickOn(CommonLocators.homeButton);

        LoggerUtility.info("Navigated to Home Page");
    }

}
