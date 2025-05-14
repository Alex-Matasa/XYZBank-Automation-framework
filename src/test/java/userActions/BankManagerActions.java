package userActions;

import dataObjects.Accounts;
import dataObjects.Customers;
import extentUtility.ExtentUtility;
import extentUtility.StepType;
import helperMethods.StringUtils;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.bankManager.AddCustomerPage;
import pageObjects.bankManager.BankManagerFacade;
import pageObjects.bankManager.CustomersPage;
import pageObjects.bankManager.OpenAccountPage;
import validation.ActualMessages;
import validation.ValidationUtils;

import java.util.List;

public class BankManagerActions {

    private final WebDriver driver;
    private BankManagerFacade bankManagerFacade;
    private CustomersPage customersPage;

    public BankManagerActions(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateToPage(String pageName) {
        bankManagerFacade = new BankManagerFacade(driver);

        bankManagerFacade.navigateToPage(pageName);
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Navigated to " + pageName);
    }

    public void addCustomer(Customers customer) {
        bankManagerFacade = new BankManagerFacade(driver);
        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);

        addCustomerPage.enterFirstName(customer.getFirstName());
        addCustomerPage.enterLastName(customer.getLastName());
        addCustomerPage.enterPostCode(customer.getPostCode());
        addCustomerPage.clickOnSubmitButton(customer.getFirstName(), customer.getLastName(), customer.getPostCode());
        ExtentUtility.addTestLog(StepType.INFO_STEP, "Filled form and submitted");

        if (ActualMessages.getActualMessage().contains("id")) {
            customer.setCustomerId(ActualMessages.getActualMessage().split(":")[1].trim());
            customer.setFullName();
        }
    }

    public void openAccount(Customers customer, Accounts account) {
        OpenAccountPage openAccountPage = new OpenAccountPage(driver);

        openAccountPage.selectCustomer(customer.getFullName());
        openAccountPage.selectCurrency(account.getCurrency());
        account.setAccountId(openAccountPage.clickOnProcessButton(customer.getFullName(), account.getCurrency()));

        if (!account.getAccountId().isEmpty()) {
            LoggerUtility.info("An id number was provided for the account");
            customer.getAccounts().add(account);
        } else LoggerUtility.info("NO id number was provided for the account");
    }

    public void sortByFirstName(boolean ascending) {
        customersPage = new CustomersPage(driver);

        if (ascending) {
            customersPage.clickOnFirstNameButton();
            customersPage.clickOnFirstNameButton();
        } else customersPage.clickOnFirstNameButton();

        LoggerUtility.info("Clicked on First Name button");
    }

    public void sortByLastName(boolean ascending) {
        customersPage = new CustomersPage(driver);
        if (ascending) {
            customersPage.clickOnLastNameButton();
            customersPage.clickOnLastNameButton();
        } else customersPage.clickOnLastNameButton();

        LoggerUtility.info("Clicked on Last Name button");
    }

    public void sortByPostCode(boolean ascending) {
        customersPage = new CustomersPage(driver);
        if (ascending) {
            customersPage.clickOnPostCodeButton();
            customersPage.clickOnPostCodeButton();

        } else customersPage.clickOnPostCodeButton();

        LoggerUtility.info("Clicked on Post Code button");
    }

    public boolean isCustomerInTheTable(Customers customer) {
        customersPage = new CustomersPage(driver);
        boolean isCustomerInTheList;
        String expectedCustomer = String.join(" ", customer.getFirstName(), customer.getLastName(), customer.getPostCode());
        isCustomerInTheList = ValidationUtils.isStringInList(expectedCustomer, customersPage.getListOfCustomers());

        if (isCustomerInTheList) LoggerUtility.info("The Customer is added to the list");
        else LoggerUtility.info(("The Customer was not added to the list"));

        return isCustomerInTheList;
    }

    public boolean isCustomerDuplicated(Customers customer) {
        customersPage = new CustomersPage(driver);
        String expectedCustomer = String.join(" ", customer.getFirstName(), customer.getLastName(), customer.getPostCode());

        int count = 0;

        for (String actual : customersPage.getListOfCustomers()) {
            if (expectedCustomer.equals(actual)) {
                count++;
            }
            if (count == 2) {
                LoggerUtility.info("The Customer is duplicated");
                ExtentUtility.addTestLog(StepType.FAIL_STEP, "Customer was duplicated");
                return true;
            }
        }
        LoggerUtility.info("The Customer was not duplicated");

        return false;
    }

    public boolean validateSearchCustomerSingleMatch(String textToCheck, int numberOfMatches) {
        return ValidationUtils.textContainsString(textToCheck, customersPage.getListOfCustomers()) && customersPage.getListOfCustomers().size() == numberOfMatches;
    }

    public boolean validateSearchMultipleMatches(String textToCheck, int numberOfMatches) {
        return ValidationUtils.allTheElementsContainText(textToCheck, customersPage.getListOfCustomers()) && customersPage.getListOfCustomers().size() == numberOfMatches;
    }

    public boolean validateFilteredCustomers(String charsToCheck) {
        return ValidationUtils.allTheElementsContainText(charsToCheck, customersPage.getListOfCustomersLowerCase());
    }

    public boolean isAccountAddedToTheList(Customers customer) {
        customersPage = new CustomersPage(driver);
        List<String> actualCustomerInfo = customersPage.getListOfCustomers();

        StringBuilder expectedCustomerInfo = new StringBuilder(customer.getFirstName());
        expectedCustomerInfo.append(" ").append(customer.getLastName());
        expectedCustomerInfo.append(" ").append(customer.getPostCode());

        for (int i = 0; i < customer.getAccounts().size(); i++) {
            expectedCustomerInfo.append(" ").append(customer.getAccounts().get(i).getAccountId());
        }

        for (String row : actualCustomerInfo) {
            if (row.equals(expectedCustomerInfo.toString())) {
                LoggerUtility.info("Account is present in transactions table");
                return true;
            }
        }

        return false;
    }

    public boolean isAccountCreated(Accounts account) {
        if (account.getAccountId().isEmpty()) {
            LoggerUtility.info("The account was not created");
            return false;
        }
        return true;
    }

    public void searchOrFilterCustomers(String infoToSearchFor) {
        customersPage = new CustomersPage(driver);

        customersPage.clickOnSearchButton(infoToSearchFor);
        LoggerUtility.info("Text was typed");
    }

    public boolean validateSortedByFirstName(boolean ascending) {
        List<String> sortedList = StringUtils.extractSublist(customersPage.getListOfCustomers(), 0);
        return ValidationUtils.listIsSorted(sortedList, ascending);
    }

    public boolean validateSortedByLastName(boolean ascending) {
        List<String> sortedList = StringUtils.extractSublist(customersPage.getListOfCustomers(), 1);
        return ValidationUtils.listIsSorted(sortedList, ascending);
    }

    public boolean validateSortedByPostCode(boolean ascending) {
        List<String> sortedList = StringUtils.extractSublist(customersPage.getListOfCustomers(), 2);
        return ValidationUtils.listIsSorted(sortedList, ascending);
    }

    public void deleteCustomer(Customers customer) {
        customersPage = new CustomersPage(driver);

        String expectedCustomer = String.join(" ", customer.getFirstName(), customer.getLastName(), customer.getPostCode(), customer.getAccounts().get(0).getAccountId());

        Assert.assertTrue(ValidationUtils.isStringInList(expectedCustomer, customersPage.getListOfCustomers()));
        customersPage.clickOnDeleteButton();
    }
}

