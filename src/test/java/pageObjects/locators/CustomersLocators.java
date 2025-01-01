package pageObjects.locators;

import org.openqa.selenium.By;

public class CustomersLocators {

    public static final By allCustomersInfo = By.xpath("//tbody/tr//*[contains(@class, 'ng-binding')]");
    public static final By customersList = By.xpath("//tbody/tr");
    public static final By searchField = By.xpath("//input[@placeholder='Search Customer']");
    public static final By deleteButton = By.xpath(".//td/button");
    public static final By customerInfo = By.xpath("//*[contains(@class, 'ng-binding')]");
}
