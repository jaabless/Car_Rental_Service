package com.carrentalservice.ui_tests.tests;

import com.carrentalservice.pages.BusinessRegistrationPage;
import com.carrentalservice.pages.DecisionPage;
import com.carrentalservice.pages.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusinessRegistrationTests extends BaseTest{

    @BeforeEach
    public void setup() {
       LoginPage loginPage = homepage.clickLogin();
       DecisionPage decisionPage = loginPage.clickRegister();
       decisionPage.clickRegisterBusiness();
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterBusinessTestData#validRegistrationData")
    @Story("Business Registration Functionality")
    @DisplayName("Verify that business owner can register successfully with valid details")
    public void testValidBusinessRegistration(String fullName, String email, String phoneNumber,
                                              String password, String confirmPassword,String companyName, String logoPath,String country, String city, String documentPath) {
        BusinessRegistrationPage businessRegistrationPage = new BusinessRegistrationPage(driver);
        businessRegistrationPage.completeBusinessRegistration(fullName, email, phoneNumber, password, confirmPassword, companyName, logoPath, country, city, documentPath);
        assertTrue(businessRegistrationPage.isReviewPageDisplayed());
        assertEquals("Your Account Is Being Reviewed", businessRegistrationPage.getReviewMessage());
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterBusinessTestData#invalidAccountData")
    @Story("Business Registration Functionality")
    @DisplayName("Verify that registration fails with invalid account details")
    public void testRegisterWithInvalidData(
            String fullName, String email, String phoneNumber,
            String password, String confirmPassword, String expectedError) {

        BusinessRegistrationPage accountPage = new BusinessRegistrationPage(driver);
        accountPage.completeAccountDetails(fullName, email, phoneNumber, password, confirmPassword);

//        assertTrue(accountPage.isInputErrorVisible(), "Error should be visible");
        assertEquals(expectedError, accountPage.getInputErrorMessage(), "Unexpected error message");
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.RegisterBusinessTestData#validAccountData")
    @Story("Business Registration Functionality")
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
    @Story("Business Registration Functionality")
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

//    @ParameterizedTest
//    @MethodSource("testdata.RegisterTestData#validRegistrationData")
//    @DisplayName("End-to-End: Full Registration")
//    void testFullRegistration(
//            String name, String email, String phone, String pwd, String confirmPwd,
//            String company, String logoPath, String city, String docPath) {
//
//        driver.get("https://d2mld9h0uxnhq4.cloudfront.net/register-business");
//        accountPage = new AccountDetailsPage(driver);
//
//        CompanyDetailsPage companyPage = accountPage.enterFullName(name)
//                .enterEmail(email)
//                .enterPhone(phone)
//                .enterPassword(pwd)
//                .confirmPassword(confirmPwd)
//                .acceptTerms()
//                .clickNext();
//
//        assertTrue(companyPage.isOnPage(), "Should navigate to Company Details");
//
//        companyPage.enterCompanyName(company)
//                .uploadLogo(logoPath)
//                .enterCity(city)
//                .uploadDocument(docPath)
//                .clickFinish();
//
//        // Adjust based on actual success behavior
//        String currentUrl = driver.getCurrentUrl();
//        assertTrue(currentUrl.contains("success") || currentUrl.contains("dashboard"),
//                "Should redirect to success/dashboard page after registration");
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Edge Case: Missing Terms Agreement")
//    void testMissingTerms() {
//        driver.get("https://d2mld9h0uxnhq4.cloudfront.net/register-business");
//        accountPage = new AccountDetailsPage(driver);
//
//        accountPage.enterFullName("John Doe")
//                .enterEmail("john@test.com")
//                .enterPhone("+1234567890")
//                .enterPassword("Pass123!")
//                .confirmPassword("Pass123!")
//                // Skip acceptTerms()
//                .clickNext();
//
//        assertTrue(accountPage.isFIleErrorVisible());
//        assertTrue(accountPage.getErrorMessage().toLowerCase().contains("terms"));
//    }
}
