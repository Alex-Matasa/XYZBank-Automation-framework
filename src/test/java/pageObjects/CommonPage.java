package pageObjects;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage extends BasePage{

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@ng-click='home()']")
    private WebElement homeButton;

    public void clickOnHomeButton() {
        webElementsMethods.clickOn(homeButton);
        LoggerUtility.info("Clicked on Home Button");
    }

}
