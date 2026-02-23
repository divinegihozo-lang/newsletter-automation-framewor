package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.pages.SuccessPage;
import com.newsletter.utils.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Success Card Tests")
class SuccessCardTest extends TestBase {

        private SuccessPage successPage;

        @BeforeEach
        void initAndSubscribe() {
                successPage = newsletterPage.submitValidEmail(TestData.SUCCESS_CARD_EMAIL);
        }

        // ── Success card visibility ───────────────────────────────────────────────

        @Test
        @DisplayName("Verify that success card is visible after valid submission")
        void verifyThatSuccessCardIsVisible() {
                assertTrue(successPage.isSuccessCardVisible(),
                                "Success card should be visible after valid submission");
        }

        @Test
        @DisplayName("Verify that newsletter card is hidden after valid submission")
        void verifyThatNewsletterCardIsHidden() {
                assertFalse(newsletterPage.isNewsletterCardVisible(),
                                "Newsletter card should be hidden after successful submission");
        }

        // ── Content checks ────────────────────────────────────────────────────────

        @Test
        @DisplayName("Verify that success heading reads correct text")
        void verifyThatSuccessHeadingIsCorrect() {
                assertEquals(TestData.SUCCESS_HEADING, successPage.getSuccessHeadingText(),
                                "Success heading text should match");
        }

        @Test
        @DisplayName("Verify that submitted email is displayed in the success message")
        void verifyThatConfirmedEmailMatchesSubmittedEmail() {
                assertEquals(TestData.SUCCESS_CARD_EMAIL, successPage.getConfirmedEmail(),
                                "Confirmed email should match the submitted email");
        }

        @Test
        @DisplayName("Verify that success message contains correct confirmation text")
        void verifyThatSuccessMessageContainsConfirmationText() {
                String message = successPage.getSuccessMessageText();
                assertTrue(message.contains(TestData.CONFIRMATION_MSG_CONTAINS),
                                "Success message should contain confirmation text");
                assertTrue(message.contains(TestData.ACTION_MSG_CONTAINS),
                                "Success message should contain action instructions");
        }

        @Test
        @DisplayName("Verify that check icon is visible on the success card")
        void verifyThatCheckIconIsVisible() {
                assertTrue(successPage.isCheckIconVisible(),
                                "Check/success icon should be visible");
        }

        @Test
        @DisplayName("Verify that dismiss button is visible with correct label")
        void verifyThatDismissButtonIsVisibleWithCorrectText() {
                assertTrue(successPage.isDismissButtonVisible(),
                                "Dismiss button should be visible");
                assertEquals(TestData.DISMISS_BUTTON_TEXT, successPage.getDismissButtonText(),
                                "Dismiss button should read '" + TestData.DISMISS_BUTTON_TEXT + "'");
        }

        // ── Dismiss flow ──────────────────────────────────────────────────────────

        @Test
        @DisplayName("Verify that dismiss returns to newsletter card")
        void verifyThatDismissShowsNewsletterCard() {
                successPage.clickDismiss();
                assertTrue(newsletterPage.isNewsletterCardVisible(),
                                "Newsletter card should reappear after dismiss");
        }

        @Test
        @DisplayName("Verify that email input is cleared after dismiss")
        void verifyThatDismissClearsEmailInput() {
                successPage.clickDismiss();
                String inputValue = driver.findElement(
                                org.openqa.selenium.By.id("email")).getAttribute("value");
                assertEquals("", inputValue,
                                "Email input should be empty after dismiss");
        }

        @Test
        @DisplayName("Verify that user can re-subscribe after dismissing")
        void verifyThatDismissAllowsResubmission() {
                successPage.clickDismiss();
                SuccessPage newSuccess = newsletterPage.submitValidEmail(TestData.ANOTHER_VALID_EMAIL);
                assertTrue(newSuccess.isSuccessCardVisible(),
                                "User should be able to subscribe again after dismissing");
                assertEquals(TestData.ANOTHER_VALID_EMAIL, newSuccess.getConfirmedEmail(),
                                "New email should be reflected in success card");
        }

}
