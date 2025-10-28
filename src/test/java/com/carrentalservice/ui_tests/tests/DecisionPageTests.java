package com.carrentalservice.ui_tests.tests;

import com.carrentalservice.pages.BusinessRegistrationPage;
import com.carrentalservice.pages.DecisionPage;
import com.carrentalservice.pages.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecisionPageTests extends BaseTest{


    @BeforeEach
    public void setup() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.clickRegister();
    }

    @Test
    @DisplayName("Verify Business Owner Registration Navigation")
    @Story("Decision Page Functionality")
    public void clickBusinessOwner() {
        DecisionPage decisionPage = new DecisionPage(driver);
        BusinessRegistrationPage businessRegistrationPage = decisionPage.clickRegisterBusiness();
        assertTrue(businessRegistrationPage.isLoaded());

    }

    @Test
    @DisplayName("Verify Customer Registration Navigation")
    @Story("Decision Page Functionality")
    public void clickCustomer() {
        DecisionPage decisionPage = new DecisionPage(driver);
        var customerRegistrationPage = decisionPage.clickRegisterCustomer();
        assertTrue(customerRegistrationPage.isLoaded());

    }
}
