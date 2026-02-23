package com.newsletter.data;

import java.util.stream.Stream;

/**
 * Dedicated class for test data constants and method sources.
 * Centralizing data here makes maintenance easier and keeps test files clean.
 */
public class TestData {

    // ── Emails ───────────────────────────────────────────────────────────────

    public static final String VALID_EMAIL = "user@example.com";
    public static final String VALID_EMAIL_UPPERCASE = "USER@EXAMPLE.COM";
    public static final String VALID_EMAIL_DOMAIN_ORG = "test123@domain.org";
    public static final String SIGNUP_TEST_EMAIL = "didi@example.com";
    public static final String SUCCESS_CARD_EMAIL = "divi@gmail.com";
    public static final String ANOTHER_VALID_EMAIL = "another@example.com";
    public static final String DOT_IN_LOCAL_PART_EMAIL = "user.name@example.com";

    public static final String[] INVALID_EMAILS = {
            "plaintext",
            "missing@dot",
            "@nodomain.com",
            "no@.com",
            "spaces in@email.com",
            "double@@domain.com",
            "nodot@domaincom"
    };

    // ── UI Expected Texts ────────────────────────────────────────────────────

    public static final String HEADING_STAY_UPDATED = "Stay updated!";
    public static final String SUBHEADING_CONTAINS_PM_COUNT = "60,000+";
    public static final String EMAIL_LABEL = "Email address";
    public static final String EMAIL_PLACEHOLDER = "email@company.com";
    public static final String SUBSCRIBE_BUTTON_TEXT = "Subscribe to monthly newsletter";
    public static final String ERROR_VALID_EMAIL_REQUIRED = "Valid email required";

    public static final String SUCCESS_HEADING = "Thanks for subscribing!";
    public static final String DISMISS_BUTTON_TEXT = "Dismiss message";
    public static final String CONFIRMATION_MSG_CONTAINS = "A confirmation email has been sent to";
    public static final String ACTION_MSG_CONTAINS = "Please open it and click the button inside";

    // ── Method Sources for Parameterized Tests ───────────────────────────────

    /**
     * @return Stream of invalid email formats for parameterized testing.
     */
    public static Stream<String> getInvalidEmails() {
        return Stream.of(INVALID_EMAILS);
    }

    /**
     * @return Stream of valid email formats for parameterized testing.
     */
    public static Stream<String> getValidEmails() {
        return Stream.of(VALID_EMAIL, VALID_EMAIL_UPPERCASE, VALID_EMAIL_DOMAIN_ORG);
    }
}
