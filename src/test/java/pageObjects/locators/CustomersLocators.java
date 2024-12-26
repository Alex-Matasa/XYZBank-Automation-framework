package pageObjects.locators;

import org.openqa.selenium.By;

public class CustomersLocators {

    public static final By lastCustomerAddedInfo = By.xpath("//tbody/tr[last()]//*[contains(@class, 'ng-binding')]");
    public static final By allCustomersInfo = By.xpath("//tbody/tr//*[contains(@class, 'ng-binding')]");
    public static final By customersList = By.xpath("//tbody/tr");
    public static final By homeButton = By.xpath("//button[@class='btn home']");
    public static final By searchField = By.xpath("//input[@placeholder='Search Customer']");
    public static final By deleteButton = By.xpath(".//td/button");

}
