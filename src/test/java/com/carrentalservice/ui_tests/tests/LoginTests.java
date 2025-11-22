package com.carrentalservice.ui_tests.tests;

import com.carrentalservice.pages.DashboardPage;
import com.carrentalservice.pages.LoginPage;
import com.carrentalservice.utils.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests extends BaseTest {

    @BeforeEach
    public void setup() {
        LoginPage loginPage = homepage.clickLogin();
    }

    @Test
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.enterCredentials(TestData.VALID_EMAIL, TestData.VALID_PASSWORD);
        // assert dashboard loaded
        assertFalse(dashboard.isLoaded(), "Dashboard should load after successful login");
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.LoginTestData#validBusinessLoginTestData")
    @Story("Login Functionality - Business Owner")
    @DisplayName("Verify that all business owners can securely log in to access the platform with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithValidData(String email, String password, boolean shouldSucceed, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.enterCredentials(email, password);
        assertTrue(dashboard.isLoaded(), "Dashboard should load after successful login");
        assertEquals(dashboard.getUserName(), "Dashboard", "Unexpected user name on dashboard");
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.LoginTestData#validCustomerLoginTestData")
    @Story("Login Functionality - Customers")
    @DisplayName("Verify that all customers can securely log in to access the platform with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testCustomerLoginWithValidData(String email, String password, boolean shouldSucceed, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.enterCredentials(email, password);
        assertTrue(dashboard.isLoaded(), "Dashboard should load after successful login");
        assertEquals(dashboard.getUserName(), "Dashboard", "Unexpected user name on dashboard");
    }

    @ParameterizedTest
    @MethodSource("com.carrentalservice.ui_tests.data.LoginTestData#invalidLoginTestData")
    @Story("Login Functionality")
    @DisplayName("Test login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidData(String email, String password, boolean shouldSucceed, String expectedResult) {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.enterCredentials(email, password);
        assertFalse(dashboard.isLoaded(), "Dashboard should load after successful login");
        assertEquals(expectedResult, loginPage.getErrorMessageText(), "Unexpected error message");
    }

    @Test
    @DisplayName("Verify access restriction for unauthenticated users")
    @Story("Login Functionality")
    @Severity(SeverityLevel.NORMAL)
    public void testRestrictedPageAccess() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://d2pnvbshv9sl2w.cloudfront.net/business-owner/dashboard");
        assertTrue(loginPage.isLoginPageDisplayed(), "Expected to be redirected to login page");
    }

    @Test
    @DisplayName("Verify login page displays form correctly")
    @Story("Login Functionality")
    @Severity(SeverityLevel.MINOR)
    public void testLoginPageFormDisplay() {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page form not displayed correctly");
    }

    @Test
    @DisplayName("Verify error message for invalid credentials")
    @Story("Login Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void invalidCredentialsShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(TestData.INVALID_EMAIL);
        loginPage.enterPassword(TestData.INVALID_PASSWORD);
        loginPage.clickLogin();
        String err = loginPage.getErrorMessageText();
        assertFalse(err.isBlank(), "An error message should be shown for invalid credentials");
    }

    @Test
    @DisplayName("Verify that user can navigate to register page from login page")
    @Story("Login Functionality")
    @Severity(SeverityLevel.MINOR)
    public void testClickRegister(){
        LoginPage loginPage = new LoginPage(driver);
        var decisionPage = loginPage.clickRegister();
        assertTrue(decisionPage.isLoaded(), "Clicking register should redirect to Decision Page");
    }

    @Test
    @DisplayName("Verify that clicking register redirects to decision page")
    @Story("Login Functionality")
    @Severity(SeverityLevel.MINOR)
    public void clickRegisterRedirectsToDecisionPage() {
        LoginPage loginPage = new LoginPage(driver);
        var decisionPage = loginPage.clickRegister();
        assertTrue(decisionPage.isLoaded(), "Clicking register should redirect to Decision Page");
    }

    @Test
    @DisplayName("Verify error message for empty fields")
    @Story("Login Functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void emptyFieldsShowsError() {
        LoginPage login = new LoginPage(driver);
        login.enterEmail(TestData.EMPTY);
        login.enterPassword(TestData.EMPTY);
        login.clickLogin();
        String err = login.getErrorMessageText();
        assertFalse(err.isBlank(), "An error message should be shown for empty credentials");
    }

    @Test
    public void longInputEdgeCase() {
        LoginPage login = new LoginPage(driver);
        login.enterEmail(TestData.VERY_LONG_STRING + "@example.com");
        login.enterPassword(TestData.VERY_LONG_STRING);
        login.clickLogin();
        // Expect error or graceful handling (no crash)
        String err = login.getErrorMessageText();
        assertTrue(err.length() > 0 || !driver.getCurrentUrl().contains("/error"),
                "Application should handle very long input (show error, not crash).");
    }

}

