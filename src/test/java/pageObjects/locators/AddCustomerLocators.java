package pageObjects.locators;

import org.openqa.selenium.By;

public class AddCustomerLocators {

    public static By formList = By.xpath("//form/div/input");
    public static By addCustomerSubmit = By.xpath("//button[@type='submit']");
    public static By lName = By.xpath("//input[@ng-model='lName']");
    public static By fName = By.xpath("//input[@ng-model='fName']");
    public static By postCode = By.xpath("//input[@ng-model='postCd']");

}
