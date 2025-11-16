package com.carrentalservice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

    @FindBy(xpath = "//app-primary-button[@class='hidden md:block']//span[contains(text(),'Login')]")
    private WebElement loginButton;

    public Homepage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLogin() {
        logger.info("Clicking login button on homepage");
        loginButton.click();
        return new LoginPage(driver);
    }

}
