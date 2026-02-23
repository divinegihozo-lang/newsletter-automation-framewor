package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.pages.SuccessPage;
import com.newsletter.utils.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Form Validation Tests")
class FormValidationTest extends TestBase {

        // ── Empty / blank submission ──────────────────────────────────────────────

        @Test
        @DisplayName("Verify that submitting empty field shows error message")
        void verifyThatSubmitEmptyFieldShowsError() {
                newsletterPage.submitForm();
                assertTrue(newsletterPage.isErrorVisible(),
                                "Error message should be visible after empty submission");
        }

        @Test
        @DisplayName("Verify that error message text is correct")
        void verifyThatErrorMessageTextIsCorrect() {
                newsletterPage.submitForm();
                assertEquals(TestData.ERROR_VALID_EMAIL_REQUIRED, newsletterPage.getErrorText(),
                                "Error text should read '" + TestData.ERROR_VALID_EMAIL_REQUIRED + "'");
        }

        @Test
        @DisplayName("Verify that input has error CSS class on empty submission")
        void verifyThatEmptySubmitInputHasErrorClass() {
                newsletterPage.submitForm();
                assertTrue(newsletterPage.isEmailInputInErrorState(),
                                "Email input should have 'error' CSS class");
        }

        // ── Invalid email formats ─────────────────────────────────────────────────

        @ParameterizedTest(name = "Verify that invalid email [{0}] shows error")
        @MethodSource("com.newsletter.data.TestData#getInvalidEmails")
        @DisplayName("Verify that invalid email formats show error")
        void verifyThatInvalidEmailsShowError(String invalidEmail) {
                newsletterPage.enterEmail(invalidEmail);
                newsletterPage.submitForm();
                assertTrue(newsletterPage.isErrorVisible(),
                                "Error should show for invalid email: " + invalidEmail);
                assertTrue(newsletterPage.isEmailInputInErrorState(),
                                "Input should have error class for: " + invalidEmail);
        }

        // ── Valid email formats ───────────────────────────────────────────────────

        @ParameterizedTest(name = "Verify that valid email [{0}] succeeds")
        @MethodSource("com.newsletter.data.TestData#getValidEmails")
        @DisplayName("Verify that valid email formats submit successfully")
        void verifyThatValidEmailsSubmitSuccessfully(String validEmail) {
                SuccessPage successPage = newsletterPage.submitValidEmail(validEmail);
                assertTrue(successPage.isSuccessCardVisible(),
                                "Success card should appear for valid email: " + validEmail);
        }

        // ── Error clears after valid submission ───────────────────────────────────

        @Test
        @DisplayName("Verify that error clears after valid email is submitted")
        void verifyThatErrorClearsAfterValidSubmission() {
                newsletterPage.submitForm();
                assertTrue(newsletterPage.isErrorVisible(), "Error should be visible initially");

                SuccessPage successPage = newsletterPage.submitValidEmail(TestData.VALID_EMAIL);
                assertTrue(successPage.isSuccessCardVisible(),
                                "Success card should show after valid email");
        }

        // ── Regex limitation test (known edge case) ───────────────────────────────

        @Test
        @DisplayName("Verify that regex rejects valid email with dot in local part (Known Limitation)")
        void verifyThatKnownLimitationDotInLocalPart() {

                newsletterPage.enterEmail(TestData.DOT_IN_LOCAL_PART_EMAIL);
                newsletterPage.submitForm();
                assertTrue(newsletterPage.isErrorVisible(),
                                "Known limitation: current regex rejects dots in local part");
        }
}
