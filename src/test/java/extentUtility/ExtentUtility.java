package extentUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentUtility {
    // trebuie un folder care sa stocheze atasamente(ex. screenshot-uri)
    // trebuie sa fac o metoda care initializeaza raportul
    // o metoda care sa faca un test
    // o metoda care sa inchida un test
    // o metoda care ataseaza informatii la un test
    // o metoda care face screenshot
    // o metoda care genereaza raportul

    private static ExtentReports extentReports;
    private static final String pathToProject = System.getProperty("user.dir") + "/target/extentReports/";
    private static final ConcurrentHashMap<String, ExtentTest> context = new ConcurrentHashMap<>();

    public static synchronized void initiateReport() {
        createDirectory();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(pathToProject + "report.html");
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    public static synchronized void createTest(String testName) {
        ExtentTest extentTest = extentReports.createTest(testName + " - report");
        context.put(Thread.currentThread().getName(), extentTest);
        addTestLog(StepType.INFO_STEP, "---START TEST--- " + testName);
    }

    public static synchronized void finishTest(String testName) {
        addTestLog(StepType.INFO_STEP, "---FINISH TEST--- " + testName);
    }

    public static synchronized void addTestLog(String logType, String message) {
        String currentThread = Thread.currentThread().getName();

        if(logType.equals(StepType.INFO_STEP)) {
            context.get(currentThread).log(Status.INFO, message);
        }

        if(logType.equals(StepType.PASS_STEP)) {
            context.get(currentThread).log(Status.PASS, message);
        }
    }

    public static synchronized void addTestLog(WebDriver driver, String testName, String message) {
        String currentThread = Thread.currentThread().getName();

        context.get(currentThread)
                .fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshot(driver, testName)).build());
    }

    public static synchronized void generateReport() {
        extentReports.flush();
    }

    private static void createDirectory() {
        File folder = new File(pathToProject);

        if (! folder.exists()) {
            folder.mkdir();
        }
    }

    @SneakyThrows(IOException.class)
    private static String getScreenshot(WebDriver driver, String reportName) {
        String path = pathToProject + reportName + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));
        byte[] imageBytes = IOUtils.toByteArray(Files.newInputStream(Paths.get(path)));
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
