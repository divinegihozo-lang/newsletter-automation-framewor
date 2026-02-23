package com.newsletter.tests;

import com.newsletter.data.TestData;

import com.newsletter.utils.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UI Rendering Tests")
class UIRenderingTest extends TestBase {

    // ── Content checks ────────────────────────────────────────────────────────

    @Test
    @DisplayName("Verify that page heading is correct")
    void verifyThatHeadingReadsStayUpdated() {
        assertEquals(TestData.HEADING_STAY_UPDATED, newsletterPage.getHeadingText(),
                "Main heading should read '" + TestData.HEADING_STAY_UPDATED + "'");
    }

    @Test
    @DisplayName("Verify that subheading contains expected snippet")
    void verifyThatSubheadingContainsProductManagers() {
        assertTrue(newsletterPage.getSubheadingText().contains(TestData.SUBHEADING_CONTAINS_PM_COUNT),
                "Subheading should reference " + TestData.SUBHEADING_CONTAINS_PM_COUNT);
    }

    @Test
    @DisplayName("Verify that all three feature list items are visible")
    void verifyThatFeatureItemsAreVisible() {
        assertTrue(newsletterPage.areAllFeatureItemsVisible(),
                "All three feature list items should be visible");
    }

    @Test
    @DisplayName("Verify that email label is correct")
    void verifyThatEmailLabelReadsEmailAddress() {
        assertEquals(TestData.EMAIL_LABEL, newsletterPage.getEmailLabelText(),
                "Email label should read '" + TestData.EMAIL_LABEL + "'");
    }

    @Test
    @DisplayName("Verify that input placeholder is correct")
    void verifyThatPlaceholderIsCorrect() {
        assertEquals(TestData.EMAIL_PLACEHOLDER, newsletterPage.getEmailInputPlaceholder(),
                "Placeholder text should be '" + TestData.EMAIL_PLACEHOLDER + "'");
    }

    @Test
    @DisplayName("Verify that subscribe button text is correct")
    void verifyThatSubscribeButtonHasCorrectText() {
        assertEquals(TestData.SUBSCRIBE_BUTTON_TEXT,
                newsletterPage.getSubscribeButtonText(),
                "Subscribe button text should match");
    }

    @Test
    @DisplayName("Verify that hero image is displayed")
    void verifyThatHeroImageIsDisplayed() {
        assertTrue(newsletterPage.isHeroImageDisplayed(),
                "Hero illustration should be visible");
    }

    @Test
    @DisplayName("Verify that desktop hero image src contains 'desktop'")
    void verifyThatHeroImageUsesDesktopSrcOnWideViewport() {
        // Ensure desktop width
        driver.manage().window().setSize(new Dimension(1440, 900));
        String src = newsletterPage.getHeroImageSrc();
        assertTrue(src.contains("desktop") || src.contains("illustration"),
                "Desktop viewport should use the desktop illustration. Actual src: " + src);
    }

    // ── Responsive layout ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Verify that mobile viewport (375px) shows newsletter card")
    void verifyThatMobileViewportShowsNewsletterCard() {
        driver.manage().window().setSize(new Dimension(375, 812));
        assertTrue(newsletterPage.isNewsletterCardVisible(),
                "Newsletter card should be visible at 375px mobile width");
    }

    @Test
    @DisplayName("Verify that mobile viewport (375px) shows subscribe button")
    void verifyThatMobileViewportShowsSubscribeButton() {
        driver.manage().window().setSize(new Dimension(375, 812));
        assertTrue(newsletterPage.isSubscribeButtonDisplayed(),
                "Subscribe button should be visible at 375px");
    }

}
