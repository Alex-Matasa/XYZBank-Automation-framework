package pageObjects.locators;

import org.openqa.selenium.By;

public class BankManagerFacadeLocators {

    public static final By addCustomerButton = By.xpath("//button[@ng-class='btnClass1']");
    public static final By openAccountButton = By.xpath("//button[@ng-click='openAccount()']");
    public static final By customersButton = By.xpath("//button[@ng-click='showCust()']");
}
