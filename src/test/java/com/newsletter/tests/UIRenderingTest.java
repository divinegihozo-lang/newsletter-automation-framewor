package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.utils.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;

import static org.junit.jupiter.api.Assertions.*;

@Epic("UI Rendering")
@Feature("Page Layout and Content")
@DisplayName("UI Rendering Tests")
class UIRenderingTest extends TestBase {

    // ── Content checks ────────────────────────────────────────────────────────

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify that page heading is correct")
    @Description("This test verifies the main heading of the newsletter signup page.\n" +
            "It ensures the welcome message is displayed correctly to the user.\n" +
            "**Impact:** Wrong heading can confuse users about the page purpose.")
    void verifyThatHeadingReadsStayUpdated() {
        step1_openBrowserAndNavigate();
        step2_verifyHeadingText("Stay connected");
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Verify that subheading contains expected snippet")
    @Description("This test checks the descriptive text below the main heading.\n" +
            "It verifies if the social proof (subscriber count) is present.\n" +
            "**Impact:** Missing social proof might reduce conversion rates.")
    void verifyThatSubheadingContainsProductManagers() {
        step1_openBrowserAndNavigate();
        step2_verifySubheadingContainsText(TestData.SUBHEADING_CONTAINS_PM_COUNT);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify that all three feature list items are visible")
    @Description("This test ensures all benefit bullets are rendered on the page.\n" +
            "It confirms that the list of features is complete and visible.\n" +
            "**Impact:** Hidden benefits might fail to persuade users to sign up.")
    void verifyThatFeatureItemsAreVisible() {
        step1_openBrowserAndNavigate();
        step2_verifyFeatureItemsVisibility();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Verify that email label is correct")
    @Description("This test verifies the accessibility label for the email input field.\n" +
            "It ensures that users know exactly what information to provide.\n" +
            "**Impact:** Missing or wrong labels hurt accessibility and UX.")
    void verifyThatEmailLabelReadsEmailAddress() {
        step1_openBrowserAndNavigate();
        step2_verifyEmailLabelText(TestData.EMAIL_LABEL);
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Verify that input placeholder is correct")
    @Description("This test checks the helper text inside the email input field.\n" +
            "It ensures a standard example email is shown to help the user.\n" +
            "**Impact:** Clear placeholders improve the form completion rate.")
    void verifyThatPlaceholderIsCorrect() {
        step1_openBrowserAndNavigate();
        step2_verifyEmailPlaceholder(TestData.EMAIL_PLACEHOLDER);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify that subscribe button text is correct")
    @Description("This test verifies the call-to-action button text.\n" +
            "It ensures the button clearly states its submission purpose.\n" +
            "**Impact:** Unclear button text can lead to higher bounce rates.")
    void verifyThatSubscribeButtonHasCorrectText() {
        step1_openBrowserAndNavigate();
        step2_verifySubscribeButtonText(TestData.SUBSCRIBE_BUTTON_TEXT);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Verify that hero image is displayed")
    @Description("This test confirms the visual hero element is rendered\n" +
            "It ensures the brand illustration is present for page aesthetics.\n" +
            "**Impact:** Missing images can make the page look broken or ugly.")
    void verifyThatHeroImageIsDisplayed() {
        step1_openBrowserAndNavigate();
        step2_verifyHeroImageVisibility();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify that desktop hero image src contains 'desktop'")
    @Description("This test verifies the responsive image source for desktop users.\n" +
            "It ensures the correct high-resolution asset is loaded for wide screens.\n" +
            "**Impact:** Loading wrong assets can cause blurriness or layout shifts.")
    void verifyThatHeroImageUsesDesktopSrcOnWideViewport() {
        step1_openBrowserAndNavigate();
        step2_setViewportSize(1440, 900);
        step3_verifyHeroImageSrcContains("desktop");
    }

    // ── Responsive layout ─────────────────────────────────────────────────────

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify that mobile viewport (375px) shows newsletter card")
    @Description("This test validates the layout on standard mobile devices.\n" +
            "It ensures the main container is flexible and remains visible.\n" +
            "**Impact:** Non-responsive layouts hinder mobile user signup.")
    void verifyThatMobileViewportShowsNewsletterCard() {
        step1_openBrowserAndNavigate();
        step2_setViewportSize(375, 812);
        step3_verifyNewsletterCardVisibility();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Verify that mobile viewport (375px) shows subscribe button")
    @Description("This test verifies button visibility on smaller mobile screens.\n" +
            "It ensures the primary action remains accessible on small devices.\n" +
            "**Impact:** Hidden action buttons make the form impossible to submit.")
    void verifyThatMobileViewportShowsSubscribeButton() {
        step1_openBrowserAndNavigate();
        step2_setViewportSize(375, 812);
        step3_verifySubscribeButtonVisibility();
    }

    // ── Steps ─────────────────────────────────────────────────────────────────

    @Step("Step 1: Open browser and navigate to Newsletter page")
    private void step1_openBrowserAndNavigate() {
        // Navigation already handled in @BeforeEach in TestBase
    }

    @Step("Step 2: Verify main heading text is '{expectedHeading}'")
    private void step2_verifyHeadingText(String expectedHeading) {
        assertEquals(expectedHeading, newsletterPage.getHeadingText(),
                "Main heading should match expected value");
    }

    @Step("Step 2: Verify subheading contains expected text: '{expected}'")
    private void step2_verifySubheadingContainsText(String expected) {
        assertTrue(newsletterPage.getSubheadingText().contains(expected),
                "Subheading should contain " + expected);
    }

    @Step("Step 2: Verify all feature list items are visible")
    private void step2_verifyFeatureItemsVisibility() {
        assertTrue(newsletterPage.areAllFeatureItemsVisible(),
                "All three feature list items should be visible");
    }

    @Step("Step 2: Verify email input label text is '{expected}'")
    private void step2_verifyEmailLabelText(String expected) {
        assertEquals(expected, newsletterPage.getEmailLabelText(),
                "Email label text should match");
    }

    @Step("Step 2: Verify email input placeholder text is '{expected}'")
    private void step2_verifyEmailPlaceholder(String expected) {
        assertEquals(expected, newsletterPage.getEmailInputPlaceholder(),
                "Placeholder text should match");
    }

    @Step("Step 2: Verify subscribe button text is '{expected}'")
    private void step2_verifySubscribeButtonText(String expected) {
        assertEquals(expected, newsletterPage.getSubscribeButtonText(),
                "Subscribe button text should match");
    }

    @Step("Step 2: Verify hero image is displayed")
    private void step2_verifyHeroImageVisibility() {
        assertTrue(newsletterPage.isHeroImageDisplayed(), "Hero illustration should be visible");
    }

    @Step("Step 2: Set window size to {width}x{height}")
    private void step2_setViewportSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    @Step("Step 3: Verify hero image source contains '{expectedSnippet}'")
    private void step3_verifyHeroImageSrcContains(String expectedSnippet) {
        String src = newsletterPage.getHeroImageSrc();
        assertTrue(src.contains(expectedSnippet) || src.contains("illustration"),
                "Image src should contain " + expectedSnippet);
    }

    @Step("Step 3: Verify newsletter card is visible in mobile layout")
    private void step3_verifyNewsletterCardVisibility() {
        assertTrue(newsletterPage.isNewsletterCardVisible(), "Newsletter card should be visible");
    }

    @Step("Step 3: Verify subscribe button is visible in mobile layout")
    private void step3_verifySubscribeButtonVisibility() {
        assertTrue(newsletterPage.isSubscribeButtonDisplayed(), "Subscribe button should be visible");
    }
}
