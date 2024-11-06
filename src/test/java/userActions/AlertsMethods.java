package userActions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertsMethods {

    private WebDriver driver;

    public AlertsMethods(WebDriver driver) {
        this.driver = driver;
    }

    public  String getAlertsTextAndAccept() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }


}
