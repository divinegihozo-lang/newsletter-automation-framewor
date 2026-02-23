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
public class FailingTests extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Verify that a non-existent element is present")
    @Description("This test is intentionally designed to fail by checking for a non-existent element to demonstrate reporting capabilities.\n\n"
            +
            "**Impact:** If this element were real, its absence would prevent users from completing critical actions.")
    void verifyNonExistentElementIsPresent() {
        step1_navigateToNewsletterPage();
        step2_verifyPresenceOfGhostElement(newsletterPage);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Verify that the heading text matches an incorrect value")
    @Description("This test is intentionally designed to fail by asserting an incorrect heading text.\n\n" +
            "**Impact:** Incorrect heading text can lead to user confusion regarding the page purpose.")
    void verifyHeadingTextWithIncorrectValue() {
        step1_navigateToNewsletterPage();
        step2_verifyHeadingText(newsletterPage);
    }

    @Step("Step 1: Navigate to the Newsletter Page")
    private void step1_navigateToNewsletterPage() {
    }

    @Step("Step 2: Verify the presence of a ghost element that doesn't exist")
    private void step2_verifyPresenceOfGhostElement(NewsletterPage newsletterPage) {
        assertTrue(false, "Ghost element with ID 'id-that-does-not-exist' should be visible");
    }

    @Step("Step 2: Verify that the heading text is 'Wrong Heading'")
    private void step2_verifyHeadingText(NewsletterPage newsletterPage) {
        assertEquals("Wrong Heading Text", newsletterPage.getHeadingText(),
                "Heading text should match the expected value (Intentionally failing)");
    }
}
