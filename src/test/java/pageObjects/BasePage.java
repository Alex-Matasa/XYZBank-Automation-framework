package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import helperMethods.AlertsMethods;
import helperMethods.AssertionsMethods;
import helperMethods.WebElementsMethods;

public class BasePage {

    protected WebDriver driver;
    protected AlertsMethods alertsMethods;
    protected AssertionsMethods assertionsMethods;
    protected WebElementsMethods webElementsMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        alertsMethods = new AlertsMethods(driver);
        assertionsMethods = new AssertionsMethods(driver);
        webElementsMethods = new WebElementsMethods(driver);
        PageFactory.initElements(driver, this);
    }
}
