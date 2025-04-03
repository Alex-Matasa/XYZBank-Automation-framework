package pageObjects;

import org.openqa.selenium.WebDriver;
import helperMethods.AlertsMethods;
import validation.ValidationUtils;
import helperMethods.WebElementsMethods;

public class BasePage {

    protected WebDriver driver;
    protected AlertsMethods alertsMethods;
    protected ValidationUtils validationUtils;
    protected WebElementsMethods webElementsMethods;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        alertsMethods = new AlertsMethods(driver);
        validationUtils = new ValidationUtils();
        webElementsMethods = new WebElementsMethods(driver);
    }
}
