package pageObjects.locators;

import org.openqa.selenium.By;

public class CustomersLocators {

    public static final By customersList = By.xpath("//tbody/tr");
    public static final By searchField = By.xpath("//input[@placeholder='Search Customer']");
    public static final By deleteButton = By.xpath(".//td/button");
}
