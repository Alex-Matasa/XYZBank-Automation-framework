package helperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class WebElementsMethods {

    private WebDriver driver;


    public WebElementsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOn(WebElement webElement) {
        webElement.click();
    }

    public void clickOn(By locator) {
        driver.findElement(locator).click();
    }



    public void clickOn(List<WebElement> webElementList, String text) {
        webElementList.stream().filter(s -> s.getText().equals(text)).findFirst().orElse(null).click();
    }

    public void clickOn(By locator, String text) {

        List<WebElement> webElementList = driver.findElements(locator);
        webElementList.stream().filter(s -> s.getText().equals(text)).findFirst().orElse(null).click();
    }

    public void select(WebElement webElement, String text) {
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    public void select(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public void selectLast(WebElement webElement) {
        Select select = new Select(webElement);
        select.selectByIndex(select.getOptions().size() - 1);
    }

    public void selectLast(By locator) {
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(select.getOptions().size() - 1);
    }

    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public List<String> getData(By locator) {
        List <WebElement> webElementList = driver.findElements(locator);

        return webElementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}
