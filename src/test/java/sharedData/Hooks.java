package sharedData;

import extentUtility.ExtentUtility;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Hooks extends Driver{

    private WebDriver driver;
    private String testName;

    @BeforeSuite(alwaysRun = true)
    public void initiateReport() {
        ExtentUtility.initiateReport();
    }

    @BeforeMethod(alwaysRun = true)
    public void prepareEnvironment() {
        testName = this.getClass().getSimpleName();

        ExtentUtility.createTest(testName);

        driver = getDriver();

        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");

        LoggerUtility.info("Navigated to the site");
    }

    @AfterMethod(alwaysRun = true)
    public void clearEnvironment(ITestResult testResult) {
        if(testResult.getStatus() == ITestResult.FAILURE) {
            ExtentUtility.addTestLog(driver, testName, testResult.getThrowable().getMessage());
        }

        quitDriver();

        ExtentUtility.finishTest(testName);
    }

    @AfterSuite(alwaysRun = true)
    public void generateReport() {
        ExtentUtility.generateReport();
    }
}
