package pageObjects.locators;

import org.openqa.selenium.By;

public class DepositLocators {

    public static final By amount = By.xpath("//label[text()='Amount to be Deposited :']/parent::div/input");
    public static final By depositSubmitButton = By.xpath("//button[@type='submit']");
    public static final By message = By.xpath("//span[@ng-show='message']");

}
