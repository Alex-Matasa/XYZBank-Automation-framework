package pageObjects.customer;

import dataObjects.Transactions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.BasePage;
import pageObjects.locators.TransactionLocators;

import java.util.List;

public class TransactionsPage extends BasePage {

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnBackButton() {
        webElementsMethods.clickOn(TransactionLocators.backButton);
    }

//    public boolean validateDepositHistory(Transactions transactions) {
//        boolean isInHistory = false;
//        List<WebElement> transactionHistoryList = driver.findElements(TransactionLocators.transactionHistoryList);
//
//        for (int i = 0; i < transactionHistoryList.size(); i++) {
//            List<WebElement> webElementList = transactionHistoryList.get(i).findElements(By.xpath(".//td"));
//
//            if(!webElementList.get(0).getText().equals(transactions.getDepositHistory().get(0))) continue;
//
//            if (assertionsMethods.validateText(webElementList, transactions.getDepositHistory())) isInHistory = true;
//            break;
//        }
//
//        return isInHistory;
//    }

//    public boolean validateWithdrawHistory(Transactions transactions) {
//        boolean isInHistory = false;
//
//        List<WebElement> transactionHistoryList = driver.findElements(TransactionLocators.transactionHistoryList);
//
//        for (int i = 0; i < transactionHistoryList.size(); i++) {
//            List<WebElement> webElementList = transactionHistoryList.get(i).findElements(By.xpath(".//td"));
//            if(!webElementList.get(0).getText().equals(transactions.getWithdrawHistory().get(0))) continue;
//
//            if (assertionsMethods.validateText(webElementList, transactions.getWithdrawHistory())) isInHistory = true;
//            break;
//        }
//
//        return isInHistory;
//    }


}
