package helperMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WebElementsMethods {

    private WebDriver driver;


    public WebElementsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOn(WebElement webElement) {
        webElement.click();
    }

//    public void waitAndClick(int seconds, WebElement webElement) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
//        wait.until(ExpectedConditions.);
//    }

    public void clickOnJS(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);
    }

    public void clickOn(List<WebElement> webElementList, String text) {
        webElementList.stream().filter(s -> s.getText().equals(text)).findFirst().orElse(null).click();
    }

    public void fillForm(List<WebElement> webElementList, List<String> data) {
        AtomicInteger index = new AtomicInteger(0);
        webElementList.stream().forEach(webElement -> webElement.sendKeys(data.get(index.getAndIncrement())));
    }

    public void select(WebElement webElement, String text) {
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    public void selectLast(WebElement webElement) {
        Select select = new Select(webElement);
        select.selectByIndex(select.getOptions().size() - 1);
    }



}
