package sharedData;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks extends Driver{

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void prepareEnvironment() {
        driver = getDriver();

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        LoggerUtility.info("Navigated to Home Page");
    }

    @AfterMethod(alwaysRun = true)
    public void clearEnvironment() {
        quitDriver();
    }
}
