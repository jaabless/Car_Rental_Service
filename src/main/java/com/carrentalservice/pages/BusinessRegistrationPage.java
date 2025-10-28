package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BusinessRegistrationPage extends BasePage{

    @FindBy(xpath = "//p[normalize-space()='business-registration works!']")
    private WebElement heroTitle;

    public BusinessRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            WaitUtils.waitForElementVisible(driver, heroTitle);
            logger.info("Business page loaded.");
            return heroTitle.isDisplayed();
        } catch (Exception e) {
            logger.warn("Business page not loaded: {}", e.getMessage());
            return false;
        }
    }
}
