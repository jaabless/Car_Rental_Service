package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder='enter your email address']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='********************']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[normalize-space()='Email is required']") // update selector to actual app
    private WebElement emailErrorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        navigateToBase();
        return this;
    }

    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        WaitUtils.waitForElementVisible(driver, emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        logger.info("Entering password: {}", password == null ? "null" : "[PROTECTED]");
        WaitUtils.waitForElementVisible(driver, passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password == null ? "" : password);
    }

    public void clickLogin() {
        logger.info("Clicking login button");
        WaitUtils.waitForElementClickable(driver, loginButton);
        loginButton.click();
    }

    public DecisionPage clickRegister() {
        logger.info("Clicking register button");
        WaitUtils.waitForElementClickable(driver, registerButton);
        registerButton.click();
        return new DecisionPage(driver);
    }

    public String getErrorMessageText() {
        try {
            WaitUtils.waitForElementVisible(driver, emailErrorMessage);
            String msg = emailErrorMessage.getText();
            logger.info("Error message shown: {}", msg);
            return msg;
        } catch (Exception e) {
            logger.info("No error message visible");
            return "";
        }
    }

    public DashboardPage enterCredentials(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        // optionally wait for dashboard url or element
        return new DashboardPage(driver);
    }

    public boolean isLoginPageDisplayed() {
        return emailInput.isDisplayed() && passwordInput.isDisplayed() && loginButton.isDisplayed();
    }
}

