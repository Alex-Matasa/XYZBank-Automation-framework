package pageObjects.locators;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage;

public class CommonLocators  {

    public static final By homeButton = By.xpath("//button[@ng-click='home()']");

}
