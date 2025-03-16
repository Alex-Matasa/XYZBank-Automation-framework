package pageObjects.locators;

import org.openqa.selenium.By;

public class CustomersLocators {

    public static final By customersList = By.xpath("//tbody/tr");
    public static final By searchField = By.xpath("//input[@placeholder='Search Customer']");
    public static final By firstDeleteButton = By.xpath("//tr[1]/td[5]/button");
}
