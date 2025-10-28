package com.carrentalservice.pages;


import com.carrentalservice.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: {}", url);
        return url;
    }

    public void navigateToBase() {
        logger.info("Navigating to base URL: {}", Config.BASE_URL);
        driver.get(Config.BASE_URL);
    }
}

