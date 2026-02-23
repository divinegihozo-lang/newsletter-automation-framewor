package com.newsletter.tests;

import com.newsletter.pages.NewsletterPage;
import com.newsletter.utils.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Failing Tests Demo")
@Feature("Reporting Requirements")
@DisplayName("Intentionally Failing Tests")
public class FailingTests extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify that a non-existent element is present")
    @Description("This test checks for a non-existent element to demonstrate reporting.\n" +
            "It is used to verify that screenshots are captured on failure.\n" +
            "**Impact:** Missing critical UI elements prevents users from finishing tasks.")
    void verifyNonExistentElementIsPresent() {
        step1_openBrowserAndNavigate();
        step2_verifyPresenceOfGhostElement(newsletterPage);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Verify that the heading text matches an incorrect value")
    @Description("This test asserts an incorrect heading text value intentionally.\n" +
            "It demonstrates how assertion failures are rendered in reports.\n" +
            "**Impact:** Incorrect heading text leads to user confusion and mistrust.")
    void verifyHeadingTextWithIncorrectValue() {
        step1_openBrowserAndNavigate();
        step2_verifyHeadingText(newsletterPage);
    }

    @Step("Step 1: Open browser and navigate to Newsletter page")
    protected void step1_openBrowserAndNavigate() {
        assertTrue(driver.getCurrentUrl().contains("NEWSLETTER"), "Redirect to correct page");
    }

    @Step("Step 2: Verify the presence of a ghost element")
    protected void step2_verifyPresenceOfGhostElement(NewsletterPage newsletterPage) {
        assertTrue(false, "Ghost element with ID 'id-that-does-not-exist' should be visible");
    }

    @Step("Step 2: Verify that the heading text is 'Wrong Heading'")
    protected void step2_verifyHeadingText(NewsletterPage newsletterPage) {
        assertEquals("Wrong Heading Text", newsletterPage.getHeadingText(),
                "Heading text should match the expected value (Intentionally failing)");
    }
}
