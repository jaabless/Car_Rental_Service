package com.carrentalservice.ui_tests.tests;

import com.carrentalservice.pages.BusinessRegistrationPage;
import com.carrentalservice.pages.CustomerRegistrationPage;
import com.carrentalservice.pages.DecisionPage;
import com.carrentalservice.pages.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomerRegistrationTests extends BaseTest{

    @BeforeEach
    public void setup() {
        LoginPage loginPage = homepage.clickLogin();
        DecisionPage decisionPage = loginPage.clickRegister();
        decisionPage.clickRegisterCustomer();
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterCustomerTestData#validRegistrationData")
    @Story("Customer Registration Functionality")
    @DisplayName("Verify that customer can register successfully with valid details")
    public void testValidCustomerRegistration(String fullName, String email, String phoneNumber,
                                              String password, String confirmPassword,String licenseNumber,
                                              String country, String documentPath) {
        CustomerRegistrationPage customerRegistrationPage = new CustomerRegistrationPage(driver);
        customerRegistrationPage.completeCustomerRegistration(fullName, email, phoneNumber, password, confirmPassword, licenseNumber, country, documentPath);
        assertTrue(customerRegistrationPage.isReviewPageDisplayed());
        assertEquals("Check your email", customerRegistrationPage.getReviewMessage());
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterCustomerTestData#invalidAccountData")
    @Story("Customer Registration Functionality")
    @DisplayName("Verify that registration fails with invalid account details")
    public void testRegisterWithInvalidData(
            String fullName, String email, String phoneNumber,
            String password, String confirmPassword, String expectedError) {

        CustomerRegistrationPage customerRegistrationPage = new CustomerRegistrationPage(driver);
        customerRegistrationPage.completeAccountDetails(fullName, email, phoneNumber, password, confirmPassword);

//        assertTrue(accountPage.isInputErrorVisible(), "Error should be visible");
        assertEquals(expectedError, customerRegistrationPage.getInputErrorMessage(), "Unexpected error message");
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterBusinessTestData#validAccountData")
    @Story("Customer Registration Functionality")
    @DisplayName("Verify that registration succeeds with valid account details")
    public void testRegisterWithValidData(
            String fullName, String email, String phoneNumber,
            String password, String confirmPassword) {

        BusinessRegistrationPage accountPage = new BusinessRegistrationPage(driver);
        accountPage.completeAccountDetails(fullName, email, phoneNumber, password, confirmPassword);
        accountPage.clickNextButton();

        assertTrue(accountPage.isFinishButtonVisible(), "Finish button should be visible");
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterBusinessTestData#invalidCompanyData")
    @Story("Customer Registration Functionality")
    @DisplayName("Verify that registration fails with invalid company details")
    public void testCompanyRegistrationWithInvalidData(String fullName, String email, String phoneNumber, String password, String confirmPassword,
                                                       String companyName, String logoPath, String country, String city, String documentPath, String expectedError) {
        BusinessRegistrationPage accountPage = new BusinessRegistrationPage(driver);
        accountPage.completeAccountDetails(fullName, email, phoneNumber, password, confirmPassword);
        accountPage.clickNextButton();
        BusinessRegistrationPage companyPage = new BusinessRegistrationPage(driver);
        companyPage.completeCompanyDetails(companyName, logoPath, country, city, documentPath);
//        assertTrue(companyPage.isInputErrorVisible(), "Error should be visible");
//        assertTrue(companyPage.isFIleErrorVisible(), "Error should be visible");
        assertEquals(expectedError, companyPage.getInputErrorMessage(), "Unexpected error message");
//        assertEquals(expectedError, companyPage.getFileErrorMessage(), "Unexpected error message");
    }
}
