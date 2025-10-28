package com.carrentalservice.utils;

import com.carrentalservice.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitUtils {

    public static WebElement waitForElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(Config.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}

