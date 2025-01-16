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

    public List <String> getTransactionsHistory() {
        webElementsMethods.clickOn(TransactionsLocators.dateFilter);
        return webElementsMethods.getDataAsStringList(TransactionsLocators.transactionHistoryList);
    }


}
