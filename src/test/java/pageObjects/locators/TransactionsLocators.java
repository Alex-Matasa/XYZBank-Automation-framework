package pageObjects.locators;

import org.openqa.selenium.By;

public class TransactionsLocators {

    public static final By backButton = By.xpath("//button[@ng-click='back()']");
    public static final By transactionHistoryList = By.xpath("//tr[contains(@id,'anchor')]");
    public static final By dateFilter = By.xpath("//a[contains(@ng-click,'date')]");


}
