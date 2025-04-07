package pageObjects.customer;

import org.openqa.selenium.WebDriver;
import pageObjects.BasePage;
import pageObjects.locators.TransactionsLocators;

import java.util.List;

public class TransactionsPage extends BasePage {

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnBackButton() {
        webElementsMethods.clickOn(TransactionsLocators.backButton);
    }

    public void clickOnDateFilter() {
        webElementsMethods.clickOn(TransactionsLocators.dateFilter);
    }

    public List <String> getTransactionsHistory() {
        return webElementsMethods.extractDataAsStringList(TransactionsLocators.transactionHistoryList);
    }
}
