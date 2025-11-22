package com.carrentalservice.pages;

import com.carrentalservice.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h2[normalize-space()='Dashboard']") // example
    private WebElement dashboardLabel;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        try {
            WaitUtils.waitForElementVisible(driver, dashboardLabel);
            logger.info("Dashboard loaded.");
            return dashboardLabel.isDisplayed();
        } catch (Exception e) {
            logger.warn("Dashboard not loaded: {}", e.getMessage());
            return false;
        }
    }

    public String getUserName() {
        WaitUtils.waitForElementVisible(driver, dashboardLabel);
        String msg = dashboardLabel.getText();
        logger.info("Review message : {}", msg);
        return msg;
    }
}

