package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerRegistrationPage extends BasePage{

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
}
