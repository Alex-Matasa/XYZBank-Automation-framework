package pageObjects.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginLocators {

    public static final By bankManagerLoginButton = By.xpath("//button[text() = 'Bank Manager Login']");
    public static final By customerLoginButton = By.xpath("//button[text() = 'Customer Login']");

}
