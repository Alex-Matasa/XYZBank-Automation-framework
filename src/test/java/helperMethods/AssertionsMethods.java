package helperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AssertionsMethods {

    private WebDriver driver;

    public AssertionsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public boolean actualContainsExpected(By actual, String expected){
        return driver.findElement(actual).getText().contains(expected);
    }

    public boolean actualEqualExpected(By actual, String expected) {
        return driver.findElement(actual).getText().equals(expected);
    }

}
