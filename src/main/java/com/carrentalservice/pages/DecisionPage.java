package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DecisionPage extends BasePage{

    @FindBy(xpath = "//h1[normalize-space()='Get Started with AutoHive']")
    private WebElement heroTitle;

    @FindBy(xpath = "//p[contains(text(),'List your fleet, manage bookings, and grow your re')]")
    private WebElement registerBusiness;

    @FindBy(xpath = "//p[contains(text(),'Book rental vehicles near you. Compare prices, vie')]")
    private WebElement registerCustomer;

    public DecisionPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            WaitUtils.waitForElementVisible(driver, heroTitle);
            logger.info("Decision page loaded.");
            return heroTitle.isDisplayed();
        } catch (Exception e) {
            logger.warn("Dashboard not loaded: {}", e.getMessage());
            return false;
        }
    }

    public BusinessRegistrationPage clickRegisterBusiness() {
        logger.info("Clicking Register Business option");
        WaitUtils.waitForElementClickable(driver, registerBusiness);
        registerBusiness.click();
        return new BusinessRegistrationPage(driver);
    }

    public CustomerRegistrationPage clickRegisterCustomer() {
        logger.info("Clicking Register Customer option");
        WaitUtils.waitForElementClickable(driver, registerCustomer);
        registerCustomer.click();
        return new CustomerRegistrationPage(driver);
    }
}
