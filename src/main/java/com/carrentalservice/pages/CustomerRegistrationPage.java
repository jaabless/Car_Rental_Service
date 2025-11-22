package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.Paths;

public class CustomerRegistrationPage extends BasePage{

    @FindBy(xpath = "//h1[normalize-space()='Register']")
    private WebElement registerTitle;

    @FindBy(xpath = "//input[@placeholder='Jane Doe']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@placeholder='user@gmail.com']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='e.g. +233242000000']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//input[@placeholder='**********']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@placeholder='*********']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@id='terms']")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//button[normalize-space()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@placeholder='e.g. AB1234567']")
    private WebElement licenseNumberField;

    @FindBy(xpath = "//app-upload-box[@label='Logo']//span[@class='text-blue-600 hover:underline cursor-pointer'][normalize-space()='Browse']")
    private WebElement browseLogoButton;

    @FindBy(xpath = "//app-upload-box[@label='Logo']//input[@type='file']")
    private WebElement logoUploadField;

    @FindBy(xpath = "//input[@placeholder='Select your country']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@placeholder='e.g. Los Angeles']")
    private WebElement cityField;

    @FindBy(xpath = "//app-upload-box[@label='Upload Driver’s License']//input[@type='file']")
    private WebElement driversLicenseUploadField;

    @FindBy(xpath = "//button[normalize-space()='Finish']")
    private WebElement finishButton;

    @FindBy(xpath = "//button[normalize-space()='Back']")
    private WebElement backButton;

    @FindBy(xpath = "//p[contains(@class, 'danger')]")
    private WebElement inputErrorMessage;

    @FindBy(xpath = "//p[contains(@class, 'text-red-500') and contains(@class, 'text-xs')]")
    private WebElement fileErrorMessage;

    @FindBy(xpath = "//h1[normalize-space()='Check your email']")
    private WebElement getReviewMessage;

    @FindBy(xpath = "//h1[normalize-space()='Create Your Account']")
    private WebElement heroTitle;

    public CustomerRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            WaitUtils.waitForElementVisible(driver, heroTitle);
            logger.info("Customer page loaded.");
            return heroTitle.isDisplayed();
        } catch (Exception e) {
            logger.warn("Customer page not loaded: {}", e.getMessage());
            return false;
        }
    }

    public void enterFullName(String fullName) {
        logger.info("Entering full name: {}", fullName);
        WaitUtils.waitForElementVisible(driver, fullNameField);
        fullNameField.clear();
        fullNameField.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        WaitUtils.waitForElementVisible(driver, emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        logger.info("Entering phone number: {}", phoneNumber);
        WaitUtils.waitForElementVisible(driver, phoneNumberField);
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterPassword(String password) {
        logger.info("Entering password: {}", "[PROTECTED]");
        WaitUtils.waitForElementVisible(driver, passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        logger.info("Entering confirm password: {}", "[PROTECTED]");
        WaitUtils.waitForElementVisible(driver, confirmPasswordField);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickTermsCheckbox() {
        logger.info("Checking terms and conditions checkbox");
        WaitUtils.waitForElementClickable(driver, termsCheckbox);
        termsCheckbox.click();
    }

    public void clickNextButton() {
        logger.info("Clicking Next button");
        WaitUtils.waitForElementClickable(driver, nextButton);
        nextButton.click();
    }

    public void enterLicenseNumber(String licenceNumber) {
        WaitUtils.waitForElementVisible(driver, licenseNumberField);
        licenseNumberField.clear();
        licenseNumberField.sendKeys(licenceNumber);
        logger.info("Entered licence number: {}", licenceNumber);
    }

//    public void enterCountry(String country) {
//        WaitUtils.waitForElementVisible(driver, countryField);
//        countryField.clear();
//        countryField.sendKeys(country);
//    }

    public void enterCountry(String country) {

        // 1. Open the dropdown
        countryField.click();

        // 2. Build locator for the item
        By targetOption = By.xpath("//li[normalize-space()='" + country + "']");

        // 3. Find the element first
        WebElement option = driver.findElement(targetOption);

        // 4. NOW wait for the WebElement (your WaitUtils requires WebElement)
        WaitUtils.waitForElementVisible(driver, option);

        // 5. Scroll + click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }




    public void uploadDriversLicense(String filePath) {

        String absolutePath = Paths.get(filePath).toAbsolutePath().toString();
        logger.info("Uploading business document from: {}", absolutePath);
        driversLicenseUploadField.sendKeys(absolutePath);
    }

    public void clickFinishButton() {
        logger.info("Clicking Finish button");
        WaitUtils.waitForElementClickable(driver, finishButton);
        finishButton.click();
    }

    public void clickBackButton() {
        logger.info("Clicking Back button");
        WaitUtils.waitForElementClickable(driver, backButton);
        backButton.click();
    }

    public String getInputErrorMessage() {
        try {
            WaitUtils.waitForElementVisible(driver, inputErrorMessage);
            String msg = inputErrorMessage.getText();
            logger.info("Error message : {}", msg);
            return msg;
        } catch (Exception e) {
            logger.info("No error message shown");
            return "";
        }
    }

    public String getFileErrorMessage() {
        try {
            WaitUtils.waitForElementVisible(driver, fileErrorMessage);
            String msg = fileErrorMessage.getText();
            logger.info("Error message shown: {}", msg);
            return msg;
        } catch (Exception e) {
            logger.info("No error message visible");
            return "";
        }
    }

    public boolean isInputErrorVisible() {
        try {
            WaitUtils.waitForElementVisible(driver, fileErrorMessage);
            logger.info("Error message shown.");
            return fileErrorMessage.isDisplayed();
        } catch (Exception e) {
            logger.info("Error message not shown.");
            return false;
        }
    }

    public boolean isFIleErrorVisible() {
        try {
            WaitUtils.waitForElementVisible(driver, fileErrorMessage);
            logger.info("Error message is visible.");
            return fileErrorMessage.isDisplayed();
        } catch (Exception e) {
            logger.info("Error message is not visible.");
            return false;
        }
    }

    public boolean isFinishButtonVisible() {
        try {
            WaitUtils.waitForElementVisible(driver, finishButton);
            logger.info("Finish button is visible.");
            return finishButton.isDisplayed();
        } catch (Exception e) {
            logger.info("Finish button is not visible.");
            return false;
        }
    }

    public boolean isReviewPageDisplayed() {
        try {
            WaitUtils.waitForElementVisible(driver, getReviewMessage);
            logger.info("Review page is displayed.");
            return getReviewMessage.isDisplayed();
        } catch (Exception e) {
            logger.info("Review page is not displayed.");
            return false;
        }
    }

    public String getReviewMessage() {
        try {
            WaitUtils.waitForElementVisible(driver, getReviewMessage);
            String msg = getReviewMessage.getText();
            logger.info("Review message : {}", msg);
            return msg;
        } catch (Exception e) {
            logger.info("No review message shown");
            return "";
        }
    }


    public void completeCustomerRegistration(String fullName, String email, String phoneNumber,
                                             String password, String confirmPassword,String licenseNumber,
                                             String country, String documentPath) {
        enterFullName(fullName);
        enterEmail(email);
        enterPhoneNumber(phoneNumber);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickTermsCheckbox();
        clickNextButton();
        enterLicenseNumber(licenseNumber);
        enterCountry(country);
        uploadDriversLicense(documentPath);
        clickFinishButton();
    }

    public void completeAccountDetails(String fullName, String email, String phoneNumber,
                                       String password, String confirmPassword) {
        enterFullName(fullName);
        enterEmail(email);
        enterPhoneNumber(phoneNumber);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickTermsCheckbox();
//        clickNextButton();
    }

    public void completeDriversDetails(String licenseNumber, String country, String documentPath) {
        enterLicenseNumber(licenseNumber);
        enterCountry(country);
        uploadDriversLicense(documentPath);
//        clickFinishButton();
    }
}
