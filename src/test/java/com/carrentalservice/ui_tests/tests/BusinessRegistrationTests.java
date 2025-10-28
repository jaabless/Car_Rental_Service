package com.carrentalservice.ui_tests.tests;

import com.carrentalservice.pages.BusinessRegistrationPage;
import com.carrentalservice.pages.DecisionPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusinessRegistrationTests extends BaseTest{

    @BeforeEach
    public void setup() {
       DecisionPage decisionPage = loginPage.clickRegister();
       decisionPage.clickRegisterBusiness();
    }

    @Test
    public void testValidBusinessRegistration() {
        BusinessRegistrationPage businessRegistrationPage = new BusinessRegistrationPage(driver);
        businessRegistrationPage.completeBusinessRegistration("Full name", "email@email.com", "+233245323651", "Password1!", "Password1!", "Business Name", "123 Business St");
        System.out.println("Running testValidBusinessRegistration");
        // Implement test logic for valid business registration
    }

    @Test
    public void testInvalidBusinessRegistration() {
        BusinessRegistrationPage businessRegistrationPage = new BusinessRegistrationPage(driver);
        System.out.println("Running testInvalidBusinessRegistration");
        // Implement test logic for invalid business registration
    }
}
