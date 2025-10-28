package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    @FindBy(css = "header .user-name") // example
    private WebElement userNameLabel;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            WaitUtils.waitForElementVisible(driver, userNameLabel);
            logger.info("Dashboard loaded.");
            return userNameLabel.isDisplayed();
        } catch (Exception e) {
            logger.warn("Dashboard not loaded: {}", e.getMessage());
            return false;
        }
    }
}

