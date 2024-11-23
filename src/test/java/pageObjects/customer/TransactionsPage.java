package pageObjects.customer;

import dataObjects.TransactionsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage;

import java.util.List;

public class TransactionsPage extends BasePage {

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@ng-click='back()']")
    private WebElement backButton;
    @FindBy(xpath = "//tr[contains(@id,'anchor')]")
    private List<WebElement> transactionHistoryList;

    public void clickOnBackButton() {
        webElementsMethods.clickOn(backButton);
    }

    public boolean validateDepositHistory(TransactionsData transactionsData) {
        boolean isInHistory = false;

        for (int i = 0; i < transactionHistoryList.size(); i++) {
            List<WebElement> webElementList = transactionHistoryList.get(i).findElements(By.xpath(".//td"));

            if(!webElementList.get(0).getText().equals(transactionsData.getDepositHistory().get(0))) continue;

            if (assertionsMethods.validateText(webElementList,transactionsData.getDepositHistory())) isInHistory = true;
            break;
        }

        return isInHistory;

    }

    public boolean validateWithdrawHistory(TransactionsData transactionsData) {
        boolean isInHistory = false;

        for (int i = 0; i < transactionHistoryList.size(); i++) {
            List<WebElement> webElementList = transactionHistoryList.get(i).findElements(By.xpath(".//td"));
            if(!webElementList.get(0).getText().equals(transactionsData.getWithdrawHistory().get(0))) continue;

            if (assertionsMethods.validateText(webElementList,transactionsData.getWithdrawHistory())) isInHistory = true;
            break;
        }
        return isInHistory;
    }


}
