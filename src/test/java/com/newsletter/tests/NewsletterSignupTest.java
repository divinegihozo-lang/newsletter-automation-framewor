package com.newsletter.tests;

import com.newsletter.data.TestData;
import com.newsletter.pages.SuccessPage;
import com.newsletter.utils.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Newsletter Feature")
@Feature("Signup")
public class NewsletterSignupTest extends TestBase {

    @Test
    @Story("Verify that user subscribes with valid email")
    @Description("Verify that user can subscribe successfully with valid email")
    void verifyThatUserSubscribesSuccessfully() {
        SuccessPage successPage = newsletterPage.submitValidEmail(TestData.SIGNUP_TEST_EMAIL);

        assertTrue(successPage.isSuccessCardVisible(), "Success card should be visible");
        assertEquals(TestData.SUCCESS_HEADING, successPage.getSuccessHeadingText(), "Success heading should match");
    }
}
