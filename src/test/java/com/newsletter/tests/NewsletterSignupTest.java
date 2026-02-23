package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.pages.SuccessPage;
import com.newsletter.utils.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Newsletter Feature")
@Feature("Successful Signup")
@DisplayName("Newsletter Signup Tests")
public class NewsletterSignupTest extends TestBase {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Subscribes with Valid Email")
    @DisplayName("Verify that user subscribes successfully with a valid email")
    @Description("This test covers the primary happy path of the application.\n" +
            "It ensures that a valid email address leads to the success screen.\n" +
            "**Impact:** Core business functionality. Failure prevents all new subscriptions.")
    void verifyThatUserSubscribesSuccessfully() {
        step1_openBrowserAndNavigate();
        SuccessPage successPage = step2_submitValidEmail(TestData.SIGNUP_TEST_EMAIL);
        step3_verifySuccessScreen(successPage);
    }

    // ── Steps ─────────────────────────────────────────────────────────────────

    @Step("Step 1: Open browser and navigate to Newsletter page")
    private void step1_openBrowserAndNavigate() {
        // Handled by TestBase
    }

    @Step("Step 2: Enter valid email '{email}' and submit form")
    private SuccessPage step2_submitValidEmail(String email) {
        return newsletterPage.submitValidEmail(email);
    }

    @Step("Step 3: Verify that the success card and heading are displayed")
    private void step3_verifySuccessScreen(SuccessPage successPage) {
        assertTrue(successPage.isSuccessCardVisible(), "Success card should be visible");
        assertEquals(TestData.SUCCESS_HEADING, successPage.getSuccessHeadingText(), "Success heading should match");
    }
}
