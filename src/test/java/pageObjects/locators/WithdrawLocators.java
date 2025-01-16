package pageObjects.locators;

import org.openqa.selenium.By;

public class WithdrawLocators {

    public static final By submitWithdrawButton = By.xpath("//form[@ng-submit='withdrawl()']/button");
    public static final By amountToBeWithdrawn = By.xpath("//form[@ng-submit='withdrawl()']/div/input");

}
