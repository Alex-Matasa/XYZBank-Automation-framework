package helperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AssertionsMethods {

    private WebDriver driver;

    public AssertionsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public boolean actualContainsExpected(By actual, String expected){

        return driver.findElement(actual).getText().contains(expected);
    }

    public boolean actualContainsExpected(String actual, String expected) {
        return actual.contains(expected);
    }

    public boolean actualEqualExpected(By actual, String expected) {
        return driver.findElement(actual).getText().equals(expected);
    }

    public boolean actualEqualExpected(String actual, String expected) {
        return actual.equals(expected);
    }

    public boolean actualEqualExpected(List<String> actual, List<String> expected) {
        return actual.equals(expected);
    }



}
