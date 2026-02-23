package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.pages.SuccessPage;
import com.newsletter.utils.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Newsletter Feature")
@Feature("Success Card")
@DisplayName("Success Card Tests")
class SuccessCardTest extends TestBase {

        private SuccessPage successPage;

        @BeforeEach
        void initAndSubscribe() {
                step1_openBrowserAndNavigate();
                successPage = step2_subscribeWithValidEmail(TestData.SUCCESS_CARD_EMAIL);
        }

        // ── Success card visibility ───────────────────────────────────────────────

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that success card is visible after valid submission")
        @Description("This test confirms the transition to the success state.\n" +
                        "It ensures the overlay or card is rendered upon form completion.\n" +
                        "**Impact:** Users need positive confirmation that their signup worked.")
        void verifyThatSuccessCardIsVisible() {
                step3_verifySuccessCardVisibility(true);
        }

        @Test
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Verify that newsletter card is hidden after valid submission")
        @Description("This test ensures the signup form disappears when success is shown.\n" +
                        "It prevents UI clutter by verifying mutual exclusivity of the cards.\n" +
                        "**Impact:** Showing both cards simultaneously results in a broken UI.")
        void verifyThatNewsletterCardIsHidden() {
                step3_verifyNewsletterCardVisibility(false);
        }

        // ── Content checks ────────────────────────────────────────────────────────

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that success heading reads correct text")
        @Description("This test verifies the 'Thanks for subscribing!' message.\n" +
                        "It ensures the tone and content match the design specifications.\n" +
                        "**Impact:** Wrong confirmation text can confuse or alienate the user.")
        void verifyThatSuccessHeadingIsCorrect() {
                step3_verifySuccessHeading(TestData.SUCCESS_HEADING);
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that submitted email is displayed in the success message")
        @Description("This test confirms the system reflects the user's specific email.\n" +
                        "It verifies the dynamic placeholder injection in the success card.\n" +
                        "**Impact:** Displaying the wrong email leads to 'did I mistype?' anxiety.")
        void verifyThatConfirmedEmailMatchesSubmittedEmail() {
                step3_verifyConfirmedEmail(TestData.SUCCESS_CARD_EMAIL);
        }

        @Test
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Verify that success message contains correct confirmation text")
        @Description("This test checks the body text of the success notification.\n" +
                        "It ensures that both confirmation and action instructions are present.\n" +
                        "**Impact:** Users need to know what happens next (e.g. check inbox).")
        void verifyThatSuccessMessageContainsConfirmationText() {
                step3_verifySuccessMessageContent(TestData.CONFIRMATION_MSG_CONTAINS, TestData.ACTION_MSG_CONTAINS);
        }

        @Test
        @Severity(SeverityLevel.TRIVIAL)
        @DisplayName("Verify that check icon is visible on the success card")
        @Description("This test verifies the visual 'success' illustration.\n" +
                        "It ensures the checkmark icon is rendered to signal completion.\n" +
                        "**Impact:** Visual icons provide instant, language-agnostic feedback.")
        void verifyThatCheckIconIsVisible() {
                step3_verifyCheckIconVisibility();
        }

        @Test
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Verify that dismiss button is visible with correct label")
        @Description("This test checks the 'Dismiss message' button availability.\n" +
                        "It ensures the closing action is clearly labeled for the user.\n" +
                        "**Impact:** Without a clear dismiss path, users feel 'trapped' in the card.")
        void verifyThatDismissButtonIsVisibleWithCorrectText() {
                step3_verifyDismissButton(TestData.DISMISS_BUTTON_TEXT);
        }

        // ── Dismiss flow ──────────────────────────────────────────────────────────

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that dismiss returns to newsletter card")
        @Description("This test validates the functional 'Dismiss' operation.\n" +
                        "It ensures the app returns to the initial state after closing success.\n" +
                        "**Impact:** Broken navigation prevents users from seeing the main page again.")
        void verifyThatDismissShowsNewsletterCard() {
                step4_clickDismiss();
                step5_verifyNewsletterCardVisibility(true);
        }

        @Test
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Verify that email input is cleared after dismiss")
        @Description("This test ensures the form state is reset after completion.\n" +
                        "It prevents data leakage and prepares the form for a new entry.\n" +
                        "**Impact:** Leaving sensitive data in the input is a privacy concern.")
        void verifyThatDismissClearsEmailInput() {
                step4_clickDismiss();
                step5_verifyEmailInputIsEmpty();
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that user can re-subscribe after dismissing")
        @Description("This test confirms the form's re-entrancy capability.\n" +
                        "It ensures that multiple signups are possible in a single session.\n" +
                        "**Impact:** If the form doesn't reset, it breaks for power users/families.")
        void verifyThatDismissAllowsResubmission() {
                step4_clickDismiss();
                SuccessPage newSuccess = step5_subscribeWithAnotherEmail(TestData.ANOTHER_VALID_EMAIL);
                step6_verifyResubmission(newSuccess, TestData.ANOTHER_VALID_EMAIL);
        }

        // ── Steps ─────────────────────────────────────────────────────────────────

        @Step("Step 1: Open browser and navigate to Newsletter page")
        private void step1_openBrowserAndNavigate() {
        }

        @Step("Step 2: Submit form with valid email '{email}'")
        private SuccessPage step2_subscribeWithValidEmail(String email) {
                return newsletterPage.submitValidEmail(email);
        }

        @Step("Step 3: Verify that success card visibility is {visible}")
        private void step3_verifySuccessCardVisibility(boolean visible) {
                assertEquals(visible, successPage.isSuccessCardVisible(), "Success card visibility mismatch");
        }

        @Step("Step 3: Verify that newsletter card visibility is {visible}")
        private void step3_verifyNewsletterCardVisibility(boolean visible) {
                assertEquals(visible, newsletterPage.isNewsletterCardVisible(), "Newsletter card visibility mismatch");
        }

        @Step("Step 3: Verify success heading text is '{expected}'")
        private void step3_verifySuccessHeading(String expected) {
                assertEquals(expected, successPage.getSuccessHeadingText(), "Success heading mismatch");
        }

        @Step("Step 3: Verify confirmed email text is '{expected}'")
        private void step3_verifyConfirmedEmail(String expected) {
                assertEquals(expected, successPage.getConfirmedEmail(), "Confirmed email mismatch");
        }

        @Step("Step 3: Verify success message contains '{c1}' and '{c2}'")
        private void step3_verifySuccessMessageContent(String c1, String c2) {
                String msg = successPage.getSuccessMessageText();
                assertTrue(msg.contains(c1) && msg.contains(c2), "Success message content mismatch");
        }

        @Step("Step 3: Verify check icon is visible")
        private void step3_verifyCheckIconVisibility() {
                assertTrue(successPage.isCheckIconVisible(), "Check icon should be visible");
        }

        @Step("Step 3: Verify dismiss button text is '{expected}'")
        private void step3_verifyDismissButton(String expected) {
                assertTrue(successPage.isDismissButtonVisible(), "Dismiss button should be visible");
                assertEquals(expected, successPage.getDismissButtonText(), "Dismiss button text mismatch");
        }

        @Step("Step 4: Click the 'Dismiss message' button")
        private void step4_clickDismiss() {
                successPage.clickDismiss();
        }

        @Step("Step 5: Verify that newsletter card is visible again")
        private void step5_verifyNewsletterCardVisibility(boolean visible) {
                step3_verifyNewsletterCardVisibility(visible);
        }

        @Step("Step 5: Verify that email input field is empty")
        private void step5_verifyEmailInputIsEmpty() {
                String val = driver.findElement(org.openqa.selenium.By.id("email")).getAttribute("value");
                assertEquals("", val, "Email input should be empty");
        }

        @Step("Step 5: Subscribe again with email '{email}'")
        private SuccessPage step5_subscribeWithAnotherEmail(String email) {
                return newsletterPage.submitValidEmail(email);
        }

        @Step("Step 6: Verify second subscription was successful for '{email}'")
        private void step6_verifyResubmission(SuccessPage newSuccess, String email) {
                assertTrue(newSuccess.isSuccessCardVisible(), "Second success card should be visible");
                assertEquals(email, newSuccess.getConfirmedEmail(), "Second email mismatch");
        }
}
