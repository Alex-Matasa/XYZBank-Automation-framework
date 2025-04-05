package helperMethods;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WebElementsMethods {

    private WebDriver driver;


    public WebElementsMethods(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOn(By locator) {
        driver.findElement(locator).click();
    }

    public void selectByText (By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public void sendKeys(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public List<String> extractDataAsStringList(By locator) {

        List <WebElement> webElementList = driver.findElements(locator);

        return webElementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getAlertTextForEmptyElement(By locator) {

        WebElement elementField = driver.findElement(locator);

        return (String) ((JavascriptExecutor) driver).executeScript( "return arguments[0].validationMessage;", elementField);
    }

    public boolean isElementVisible(By locator) {
        WebElement element = driver.findElement(locator);
        return element.isDisplayed();
    }

    public String getTextFromElement(By locator) {
        WebElement element = driver.findElement(locator);
        return  element.getText();
    }

    public void waitWhileVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver1 -> {
            try {
                return !driver1.findElement(locator).isDisplayed();
            } catch (NoSuchElementException e) {
                return true; // Treat "not found" as "not visible"
            }
        });
    }
}
