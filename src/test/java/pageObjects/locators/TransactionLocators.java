package pageObjects.locators;

import org.openqa.selenium.By;

public class TransactionLocators {

    public static final By backButton = By.xpath("//button[@ng-click='back()']");
    public static final By transactionHistoryList = By.xpath("//tr[contains(@id,'anchor')]");

}
