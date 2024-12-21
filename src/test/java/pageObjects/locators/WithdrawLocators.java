package pageObjects.locators;

import org.openqa.selenium.By;

public class WithdrawLocators {

    public static final By amount = By.xpath("//label[text()='Amount to be Withdrawn :']/parent::div/input");
    public static final By withdrawSubmitButton = By.xpath("//button[@type='submit']");
    public static final By message = By.xpath("//span[@ng-show='message']");

}
