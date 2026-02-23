package com.newsletter.utils;

import com.newsletter.pages.NewsletterPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(ScreenshotWatcher.class)
public class TestBase {

    protected WebDriver driver;
    protected NewsletterPage newsletterPage;

    protected static final String BASE_URL = System.getProperty(
            "base.url",
            "https://bayingana.github.io/NEWSLETTER/");

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(BASE_URL);
        newsletterPage = new NewsletterPage(driver);
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
