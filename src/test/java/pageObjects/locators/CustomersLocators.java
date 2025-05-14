package pageObjects.locators;

import org.openqa.selenium.By;

public class CustomersLocators {

    public static final By customersList = By.xpath("//tbody/tr");
    public static final By searchField = By.xpath("//input[@placeholder='Search Customer']");
    public static final By deleteButton = By.xpath("//td/button");
    public static final By firstNameButton = By.xpath("//a[normalize-space()='First Name']");
    public static final By lastNameButton = By.xpath("//a[normalize-space()='Last Name']");
    public static final By postCodeButton = By.xpath("//a[normalize-space()='Post Code']");
}
