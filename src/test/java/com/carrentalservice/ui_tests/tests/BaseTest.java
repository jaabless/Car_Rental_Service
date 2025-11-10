package com.carrentalservice.ui_tests.tests;


import com.carrentalservice.config.Config;
import com.carrentalservice.pages.Homepage;
import com.carrentalservice.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver;
//    protected static LoginPage loginPage;
    protected static Homepage homepage;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeAll
    public void globalSetup() {
        logger.info("Setting up WebDriverManager for ChromeDriver");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("Starting test: {}", testInfo.getDisplayName());
        ChromeOptions options = new ChromeOptions();
        // allow headless toggle with -Dheadless=true
        String headless = System.getProperty("headless", "true");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--no-sandbox"); // Required in Docker
        options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issues
        options.addArguments("--remote-allow-origins=*"); // Fix for some newer ChromeDriver issues
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        logger.info("Navigating to base URL: {}", Config.BASE_URL);
        driver.get(Config.BASE_URL);

        homepage = new Homepage(driver);
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("Finished test: {}", testInfo.getDisplayName());
        if (driver != null) {
            driver.quit();
        }
    }
}

