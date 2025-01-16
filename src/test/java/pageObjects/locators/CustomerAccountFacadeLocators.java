package pageObjects.locators;

import org.openqa.selenium.By;

public class CustomerAccountFacadeLocators {

    public static final By welcome = By.xpath("//span[contains(@class, 'ng-binding')]");
    public static final By pleaseOpenAccountMessage = By.xpath("//span[@ng-show='noAccount']");
    public static final By accountInfoDisplayedList = By.xpath("//strong[@class='ng-binding']");
    public static final By transactionsButton = By.xpath("//button[@ng-click='transactions()']");
    public static final By depositButton = By.xpath("//button[@ng-click='deposit()']");
    public static final By withdrawlButton = By.xpath("//button[@ng-click='withdrawl()']");
    public static final By logoutButton = By.xpath("//button[@ng-click='byebye()']");
    public static final By selectAccountId = By.xpath("//select");
    public static final By amount = By.xpath("//input[@ng-model='amount']");
    public static final By submitTransactionButton = By.xpath("//button[@type='submit']");
    public static final By message = By.xpath("//span[@ng-show='message']");
}
