package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.pages.SuccessPage;
import com.newsletter.utils.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Newsletter Feature")
@Feature("Form Validation")
@DisplayName("Form Validation Tests")
class FormValidationTest extends TestBase {

        // ── Empty / blank submission ──────────────────────────────────────────────

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that submitting empty field shows error message")
        @Description("This test checks the form's reaction to an empty email field.\n" +
                        "It ensures that submission is blocked until a value is provided.\n" +
                        "**Impact:** Prevents blank submissions from reaching the database.")
        void verifyThatSubmitEmptyFieldShowsError() {
                step1_openBrowserAndNavigate();
                step2_submitForm();
                step3_verifyErrorVisibility(true);
        }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that error message text is correct")
        @Description("This test verifies the specific wording of the validation error.\n" +
                        "It ensures the user receives clear instructions on how to fix the field.\n" +
                        "**Impact:** Clear error messages reduce user frustration and churn.")
        void verifyThatErrorMessageTextIsCorrect() {
                step1_openBrowserAndNavigate();
                step2_submitForm();
                step3_verifyErrorMessageText(TestData.ERROR_VALID_EMAIL_REQUIRED);
        }

        @Test
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Verify that input has error CSS class on empty submission")
        @Description("This test checks the visual state of the input field on error.\n" +
                        "It ensures the red border or similar styling is applied via CSS.\n" +
                        "**Impact:** Visual cues are essential for accessible form validation.")
        void verifyThatEmptySubmitInputHasErrorClass() {
                step1_openBrowserAndNavigate();
                step2_submitForm();
                step3_verifyEmailInputErrorState(true);
        }

        // ── Invalid email formats ─────────────────────────────────────────────────

        @ParameterizedTest(name = "Verify that invalid email [{0}] shows error")
        @MethodSource("com.newsletter.data.TestData#getInvalidEmails")
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that invalid email formats show error")
        @Description("This test iterates through several common invalid email strings.\n" +
                        "It confirms the frontend regex correctly identifies and rejects them.\n" +
                        "**Impact:** High data quality at the source reduces processing costs.")
        void verifyThatInvalidEmailsShowError(String invalidEmail) {
                step1_openBrowserAndNavigate();
                step2_enterEmail(invalidEmail);
                step3_submitForm();
                step4_verifyErrorVisibility(true);
                step5_verifyEmailInputErrorState(true);
        }

        // ── Valid email formats ───────────────────────────────────────────────────

        @ParameterizedTest(name = "Verify that valid email [{0}] succeeds")
        @MethodSource("com.newsletter.data.TestData#getValidEmails")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Verify that valid email formats submit successfully")
        @Description("This test verifies that a range of valid email patterns are accepted.\n" +
                        "It ensures legitimate users aren't blocked by overly strict regex.\n" +
                        "**Impact:** False negatives in validation directly lose potential subscribers.")
        void verifyThatValidEmailsSubmitSuccessfully(String validEmail) {
                step1_openBrowserAndNavigate();
                SuccessPage successPage = step2_submitValidEmail(validEmail);
                step3_verifySuccessCardVisibility(successPage);
        }

        // ── Error clears after valid submission ───────────────────────────────────

        @Test
        @Severity(SeverityLevel.NORMAL)
        @DisplayName("Verify that error clears after valid email is submitted")
        @Description("This test checks the recovery flow after a validation error.\n" +
                        "It ensures that providing a valid email clears previous error states.\n" +
                        "**Impact:** Residual errors after fixing fields confuse the user.")
        void verifyThatErrorClearsAfterValidSubmission() {
                step1_openBrowserAndNavigate();
                step2_submitForm();
                step3_verifyErrorVisibility(true);

                SuccessPage successPage = step4_submitValidEmail(TestData.VALID_EMAIL);
                step5_verifySuccessCardVisibility(successPage);
        }

        // ── Regex limitation test (known edge case) ───────────────────────────────

        @Test
        @Severity(SeverityLevel.MINOR)
        @DisplayName("Verify that regex rejects valid email with dot in local part (Known Limitation)")
        @Description("This test documents a known edge case in the current regex implementation.\n" +
                        "Emails like 'first.last@domain.com' are currently (incorrectly) rejected.\n" +
                        "**Impact:** Rare legitimate emails might be blocked until regex is updated.")
        void verifyThatKnownLimitationDotInLocalPart() {
                step1_openBrowserAndNavigate();
                step2_enterEmail(TestData.DOT_IN_LOCAL_PART_EMAIL);
                step3_submitForm();
                step4_verifyErrorVisibility(true);
        }

        // ── Steps ─────────────────────────────────────────────────────────────────

        @Step("Step 1: Open browser and navigate to Newsletter page")
        private void step1_openBrowserAndNavigate() {
        }

        @Step("Step 2: Click the 'Subscribe' button without entering data")
        private void step2_submitForm() {
                newsletterPage.submitForm();
        }

        @Step("Step 2: Enter email '{email}' into the input field")
        private void step2_enterEmail(String email) {
                newsletterPage.enterEmail(email);
        }

        @Step("Step 2: Enter valid email '{email}' and submit")
        private SuccessPage step2_submitValidEmail(String email) {
                return newsletterPage.submitValidEmail(email);
        }

        @Step("Step 3: Click the 'Subscribe' button")
        private void step3_submitForm() {
                newsletterPage.submitForm();
        }

        @Step("Step 3: Verify that error visibility is {visible}")
        private void step3_verifyErrorVisibility(boolean visible) {
                if (visible) {
                        assertTrue(newsletterPage.isErrorVisible(), "Error message should be visible");
                } else {
                        assertFalse(newsletterPage.isErrorVisible(), "Error message should NOT be visible");
                }
        }

        @Step("Step 3: Verify that the error text is exactly '{expected}'")
        private void step3_verifyErrorMessageText(String expected) {
                assertEquals(expected, newsletterPage.getErrorText(), "Error text mismatch");
        }

        @Step("Step 3: Verify that the email input field error highlight is {isError}")
        private void step3_verifyEmailInputErrorState(boolean isError) {
                assertEquals(isError, newsletterPage.isEmailInputInErrorState(), "Input error state mismatch");
        }

        @Step("Step 3: Verify that the success card is displayed")
        private void step3_verifySuccessCardVisibility(SuccessPage successPage) {
                assertTrue(successPage.isSuccessCardVisible(), "Success card should be visible");
        }

        @Step("Step 4: Verify that error visibility is {visible}")
        private void step4_verifyErrorVisibility(boolean visible) {
                step3_verifyErrorVisibility(visible);
        }

        @Step("Step 4: Enter valid email '{email}' and submit")
        private SuccessPage step4_submitValidEmail(String email) {
                return newsletterPage.submitValidEmail(email);
        }

        @Step("Step 5: Verify that the email input field error highlight is {isError}")
        private void step5_verifyEmailInputErrorState(boolean isError) {
                step3_verifyEmailInputErrorState(isError);
        }

        @Step("Step 5: Verify that the success card is displayed")
        private void step5_verifySuccessCardVisibility(SuccessPage successPage) {
                step3_verifySuccessCardVisibility(successPage);
        }
}
