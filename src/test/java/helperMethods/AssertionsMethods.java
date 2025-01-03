package helperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AssertionsMethods {

    private WebDriver driver;

    public AssertionsMethods(WebDriver driver) {
        this.driver = driver;
    }

//    public boolean validateText(List<WebElement> webElementList, List<String> data) {
//        AtomicInteger index = new AtomicInteger(0);
//        AtomicBoolean isValid = new AtomicBoolean(true);
//
//        webElementList.stream().forEach(webElement -> {
//            if(!webElement.getText().trim().equals(data.get(index.getAndIncrement()))) {
//                isValid.set(false);
//            }
////            index.getAndIncrement();
//        });
//        return isValid.get();
//    }

    public boolean validateText(By locator, List<String> data) {
        AtomicInteger index = new AtomicInteger(0);
        AtomicBoolean isValid = new AtomicBoolean(true);

        List <WebElement> webElementList = driver.findElements(locator);

        webElementList.stream().forEach(webElement -> {
            if(!webElement.getText().trim().equals(data.get(index.getAndIncrement()))) {
                isValid.set(false);
            }
//            index.getAndIncrement();
        });
        return isValid.get();
    }

    public boolean validatePartialText(By locator, String expected){

        return driver.findElement(locator).getText().contains(expected);
    }

    public boolean validateText(WebElement webElement, String text) {
        return webElement.getText().equals(text);
    }

    public boolean validateText(By locator, String text) {
        return driver.findElement(locator).getText().equals(text);
    }

    public boolean validateText(List<String> actual, List<String> expected) {
        return actual.equals(expected);
    }

    public boolean validatePartialText(String actual, String expected) {
        return actual.contains(expected);
    }

    public boolean validatePartialText(WebElement webElement, String expected) {
        return webElement.getText().contains(expected);
    }



}
