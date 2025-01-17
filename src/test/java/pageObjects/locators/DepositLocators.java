package pageObjects.locators;

import org.openqa.selenium.By;

public class DepositLocators {

    public static final By submitDepositButton = By.xpath("//form[@ng-submit='deposit()']/button");
    public static final By amountToBeDeposited = By.xpath("//form[@ng-submit='deposit()']/div/input");
}
