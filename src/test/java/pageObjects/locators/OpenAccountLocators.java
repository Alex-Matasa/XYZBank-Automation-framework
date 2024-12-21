package pageObjects.locators;

import org.openqa.selenium.By;

public class OpenAccountLocators {

    public static final By selectCustomer = By.xpath("//select[@name='userSelect']");
    public static final By selectCurrency = By.xpath("//select[@name='currency']");
    public static final By processButton = By.xpath("//button[text()='Process']");

}
